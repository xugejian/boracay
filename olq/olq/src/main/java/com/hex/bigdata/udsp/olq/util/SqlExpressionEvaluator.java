package com.hex.bigdata.udsp.olq.util;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL字符串表达式工具类
 * <p>
 * 说明:
 * ${} 表示必填内容，参数名称需要满足字母、数字、下划线，且以字母开头；
 * #[] 表示选填内容，#[]内部有且仅有一个${}。
 * <p>
 * 示例：
 * SELECT ABC,CBA,${field1} #[,${field2}]
 * FROM TABLE_NAME
 * WHERE 1=1 #[and ABC>${abc}]
 * AND (1=1 #[or CBA='${cba}] #[or BAC='${bac}'])
 */
public class SqlExpressionEvaluator {

    private static Logger logger = LogManager.getLogger (SqlExpressionEvaluator.class);

    // #[]
    private static final String REGEX = "(#\\[(((?!#\\[).)*)\\])";
    private static final Pattern PATTERN = Pattern.compile (REGEX);

    // ${}
    private static final String REGEX2 = "(\\$\\{([\\w ]*)\\})";
    private static final Pattern PATTERN2 = Pattern.compile (REGEX2);

    // 满足字母、数字、下划线，且以字母开头
    private static final String REGEX3 = "^[A-Za-z][A-Za-z0-9_]{0,63}$";
    private static final Pattern PATTERN3 = Pattern.compile (REGEX3);

    // @{}
    private static final String REGEX4 = "(\\@\\{([\\w, ]*)\\})";
    private static final Pattern PATTERN4 = Pattern.compile (REGEX4);

    /**
     * 解析SQL字符串并返回排序字符串
     *
     * @param sql
     * @return
     */
    public static String parseOrderBy(String sql) {
        if (StringUtils.isBlank (sql)) {
            throw new IllegalArgumentException ("SQL字符串为空");
        }
        Matcher m4 = PATTERN4.matcher (sql);
        while (m4.find ()) {
            String $exp = m4.group (1);
            String exp = m4.group (2).trim ();
            logger.debug ("#group(1):" + $exp);
            logger.debug ("#group(2):" + exp);
            return exp;
        }
        return "";
    }

    /**
     * 解析SQL字符串并批量替换表达式
     *
     * @param sql
     * @param params
     * @return
     */
    public static String parseSql(String sql, Map<String, String> params) {
        if (StringUtils.isBlank (sql)) {
            throw new IllegalArgumentException ("SQL字符串为空");
        }
        // 先处理@{}
        Matcher m4 = PATTERN4.matcher (sql);
        while (m4.find ()) {
            String $exp = m4.group (1);
            String exp = m4.group (2).trim ();
            logger.debug ("#group(1):" + $exp);
            logger.debug ("#group(2):" + exp);
            if (!exp.toUpperCase ().startsWith ("ORDER BY")) {
                exp = "ORDER BY " + exp;
            }
            sql = StringUtils.replace (sql, $exp, exp); // 替换表达式为正确的结果字符串
        }
        // 然后处理#[]
        Matcher m = PATTERN.matcher (sql);
        while (m.find ()) {
            String $exp = m.group (1); // #[ABC > ${abc}] is group(1) ===> #[ABC > ${abc}]
            String exp = m.group (2).trim (); // #[ABC > ${abc}] is group(2) ===> ABC > ${abc}
            logger.debug ("#group(1):" + $exp);
            logger.debug ("#group(2):" + exp);
            String value = evaluate (exp, params); // 正确的结果字符串
            sql = StringUtils.replace (sql, $exp, value); // 替换表达式为正确的结果字符串
        }
        // 最后处理${}
        return evaluates (sql, params);
    }

    // 解析#[]内结果字符串
    private static String evaluate(String sql, Map<String, String> params) {
        boolean flg = true;
        Matcher m = PATTERN2.matcher (sql);
        while (m.find ()) {
            String $exp = m.group (1); // ABC > ${abc}  is group(1) ===> ${abc}
            String exp = m.group (2).trim (); // ABC > ${abc} is group(2) ===> abc
            logger.debug ("$group(1):" + $exp);
            logger.debug ("$group(2):" + exp);
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet ()) {
                    String key = entry.getKey ();
                    String value = entry.getValue ();
                    logger.debug ("value:" + value);
                    if (exp.equals (key)) {
                        if (StringUtils.isNotBlank (value)) {
                            sql = StringUtils.replace (sql, $exp, value); // 替换表达式为正确的结果字符串
                            flg = false;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (flg) {
            sql = " ";
        }
        logger.debug ("sql:" + sql);
        return sql;
    }

    // 解析#[]外结果字符串
    private static String evaluates(String sql, Map<String, String> params) {
        Matcher m = PATTERN2.matcher (sql);
        while (m.find ()) {
            String $exp = m.group (1); // ABC > ${abc}  is group(1) ===> ${abc}
            String exp = m.group (2).trim (); // ABC > ${abc} is group(2) ===> abc
            logger.debug ("$group(1):" + $exp);
            logger.debug ("$group(2):" + exp);
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet ()) {
                    String key = entry.getKey ();
                    String value = entry.getValue ();
                    logger.debug ("value:" + value);
                    if (exp.equals (key)) {
                        if (StringUtils.isBlank (value)) {
                            value = "";
                        }
                        sql = StringUtils.replace (sql, $exp, value); // 替换表达式为正确的结果字符串
                        logger.debug ("sql:" + sql);
                    }
                }
            }
        }
        return sql;
    }

    /**
     * 解析SQL字符串中参数名称集合
     *
     * @param sql
     * @return
     */
    public static List<String> parseParams(String sql) {
        if (StringUtils.isBlank (sql)) {
            throw new IllegalArgumentException ("SQL字符串为空");
        }
        List<String> params = new ArrayList<> ();
        // 先处理#[]
        Matcher m = PATTERN.matcher (sql);
        while (m.find ()) {
            String $exp = m.group (1);
            String exp = m.group (2).trim ();
            logger.debug ("#group(1):" + $exp);
            logger.debug ("#group(2):" + exp);
            String name = evaluateParam (exp); // 解析参数名称
            logger.debug ("name:" + name);
            if (StringUtils.isNotBlank (name)) {
                params.add (name);
            }
            sql = StringUtils.replace (sql, $exp, ""); // 替换表达式为""
            logger.debug ("sql:" + sql);
        }
        // 再处理${}
        params.addAll (evaluateParams (sql));
        // 最后去重
        return new ArrayList (new HashSet (params));
    }

    // 解析#[]内参数名称
    private static String evaluateParam(String sql) {
        String name = "";
        Matcher m = PATTERN2.matcher (sql);
        int count = 0;
        while (m.find ()) {
            String $exp = m.group (1);
            String exp = m.group (2).trim ();
            logger.debug ("$group(1):" + $exp);
            logger.debug ("$group(2):" + exp);
            if (count == 1) {
                throw new IllegalArgumentException ("#[]内不能有多个参数！");
            } else if (StringUtils.isBlank (exp)) {
                throw new IllegalArgumentException ("参数名称为空！");
            } else if (!match (exp)) {
                throw new IllegalArgumentException ("参数名称必须满足字母、数字、下划线，且以字母开头！");
            }
            name = exp;
            count++;
        }
        return name;
    }

    // 解析#[]外参数名称集合
    private static List<String> evaluateParams(String sql) {
        List<String> params = new ArrayList<> ();
        Matcher m = PATTERN2.matcher (sql);
        while (m.find ()) {
            String $exp = m.group (1);
            String exp = m.group (2).trim ();
            logger.debug ("$group(1):" + $exp);
            logger.debug ("$group(2):" + exp);
            if (StringUtils.isBlank (exp)) {
                throw new IllegalArgumentException ("参数名称为空！");
            } else if (!match (exp)) {
                throw new IllegalArgumentException ("参数名称必须满足字母、数字、下划线，且以字母开头！");
            }
            params.add (exp);
        }
        return params;
    }

    /**
     * 解析参数名称Map
     *
     * @param sql
     * @return
     */
    public static Map<String, Boolean> parseParamMap(String sql) {
        if (StringUtils.isBlank (sql)) {
            throw new IllegalArgumentException ("SQL字符串为空");
        }
        Map<String, Boolean> params = new HashMap<> ();
        // 先处理#[]
        Matcher m = PATTERN.matcher (sql);
        while (m.find ()) {
            String $exp = m.group (1);
            String exp = m.group (2).trim ();
            logger.debug ("#group(1):" + $exp);
            logger.debug ("#group(2):" + exp);
            String name = evaluateParam (exp); // 解析参数名称
            logger.debug ("name:" + name);
            if (StringUtils.isNotBlank (name)) {
                params.put (name, false); // 选输的参数
            }
            sql = StringUtils.replace (sql, $exp, ""); // 替换表达式为""
            logger.debug ("sql:" + sql);
        }
        // 再处理${}
        List<String> list = evaluateParams (sql);
        for (String name : list) {
            params.put (name, true); // 必输的参数
        }
        return params;
    }

    // 验证参数名称
    private static boolean match(String sql) {
        Matcher matcher = PATTERN3.matcher (sql);
        return matcher.matches ();
    }

    public static void main(String[] args) {
        String sql = "SELECT ${return_field} from table_name \n"
                + "where #[ABC > ${abc}] ${abc}\n"
                + "AND (#[CBA = '${cba}'] OR #[TEST <> ${ test }]) \n"
                + "@{abc ASC, CBA desc} LIMIT ${limit}";

        /**
         * 得到真实的SQL
         */
        logger.info ("--------------------------------------------");
        Map<String, String> map = new HashMap<> ();
        map.put ("abc", "1");
        map.put ("limit", "2");
        map.put ("cba", "3");
        map.put ("test", "4");
        map.put ("return_field", "5");
        String str = parseSql (sql, map);
        logger.info ("SQL: " + str);

        /**
         * 得到参数信息
         */
        logger.info ("--------------------------------------------");
        Map<String, Boolean> paramMap = parseParamMap (sql);
        for (Map.Entry<String, Boolean> entry : paramMap.entrySet ()) {
            logger.info ("KEY: " + entry.getKey () + ", VALUE: " + entry.getValue ());
        }

        /**
         * 得到排序字符串
         */
        logger.info ("--------------------------------------------");
        String orderBy = parseOrderBy (sql);
        logger.info ("ORDER BY: " + orderBy);
    }

}
