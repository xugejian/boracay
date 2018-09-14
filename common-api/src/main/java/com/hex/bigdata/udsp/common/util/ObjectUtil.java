package com.hex.bigdata.udsp.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/4/21.
 */
public class ObjectUtil {

    /**
     * 拷贝对象（通过字段拷贝）
     * <p>
     * 获取所有public字段,包括父类字段
     *
     * @param obj1
     * @param obj2
     */
    public static void copyObjectForField(Object obj1, Object obj2) {
        Field[] fields1 = obj1.getClass().getFields(); // 获取所有public字段,包括父类字段
        Field[] fields2 = obj2.getClass().getFields(); // 获取所有public字段,包括父类字段
        if (fields1 != null && fields2 != null) {
            for (Field field1 : fields1) {
                try {
                    String name1 = field1.getName();
                    Object obj = field1.get(obj1);
                    for (Field field2 : fields2) {
                        String name2 = field2.getName();
                        if (name1.equals(name2)) {
                            field2.set(obj2, obj);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝对象（通过字段拷贝）
     * <p>
     * 获取所有字段,public和protected和private,但是不包括父类字段
     *
     * @param obj1
     * @param obj2
     */
    public static void copyObjectForDeclaredField(Object obj1, Object obj2) {
        Field[] fields1 = obj1.getClass().getDeclaredFields(); // 获取所有字段,public和protected和private,但是不包括父类字段
        Field[] fields2 = obj2.getClass().getDeclaredFields(); // 获取所有字段,public和protected和private,但是不包括父类字段
        if (fields1 != null && fields2 != null) {
            for (Field field1 : fields1) {
                try {
                    String name1 = field1.getName();
                    field1.setAccessible(true);
                    Object obj = field1.get(obj1);
                    for (Field field2 : fields2) {
                        String name2 = field2.getName();
                        if (name1.equals(name2)) {
                            field2.setAccessible(true);
                            field2.set(obj2, obj);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝对象（通过get和set方法拷贝）
     * <p>
     * 获取所有public字段,包括父类字段
     *
     * @param obj1
     * @param obj2
     */
    public static void copyObjectForGetAndSetMethod(Object obj1, Object obj2) {
        Field[] fields1 = obj1.getClass().getFields(); // 获取所有public字段,包括父类字段
        Field[] fields2 = obj2.getClass().getFields(); // 获取所有public字段,包括父类字段
        if (fields1 != null && fields2 != null) {
            for (Field field1 : fields1) {
                try {
                    String name1 = field1.getName();
                    PropertyDescriptor pd1 = new PropertyDescriptor(name1, obj1.getClass());
                    Method getMethod = pd1.getReadMethod(); // 获得get方法
                    getMethod.setAccessible(true);
                    Object obj = getMethod.invoke(obj1); // 执行get方法返回一个Object
                    for (Field field2 : fields2) {
                        String name2 = field2.getName();
                        if (name1.equals(name2)) {
                            PropertyDescriptor pd2 = new PropertyDescriptor(name2, obj2.getClass());
                            Method setMethod = pd2.getWriteMethod(); // 获得set方法
                            setMethod.setAccessible(true);
                            setMethod.invoke(obj2, obj); // 执行set方法将Object的值放入
                        }
                    }
                } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝对象（通过get和set方法拷贝）
     * <p>
     * 获取所有字段,public和protected和private,但是不包括父类字段
     *
     * @param obj1
     * @param obj2
     */
    public static void copyObjectForDeclaredGetAndSetMethod(Object obj1, Object obj2) {
        Field[] fields1 = obj1.getClass().getDeclaredFields(); // 获取所有字段,public和protected和private,但是不包括父类字段
        Field[] fields2 = obj2.getClass().getDeclaredFields(); // 获取所有字段,public和protected和private,但是不包括父类字段
        if (fields1 != null && fields2 != null) {
            for (Field field1 : fields1) {
                try {
                    String name1 = field1.getName();
                    PropertyDescriptor pd1 = new PropertyDescriptor(name1, obj1.getClass());
                    Method getMethod = pd1.getReadMethod(); // 获得get方法
                    getMethod.setAccessible(true);
                    Object obj = getMethod.invoke(obj1); // 执行get方法返回一个Object
                    for (Field field2 : fields2) {
                        String name2 = field2.getName();
                        if (name1.equals(name2)) {
                            PropertyDescriptor pd2 = new PropertyDescriptor(name2, obj2.getClass());
                            Method setMethod = pd2.getWriteMethod(); // 获得set方法
                            setMethod.setAccessible(true);
                            setMethod.invoke(obj2, obj); // 执行set方法将Object的值放入
                        }
                    }
                } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 实例化对象
     *
     * @param implClass
     * @return
     */
    public static Object newInstance(String implClass) {
        Object obj = null;
        try {
            obj = Class.forName(implClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 克隆对象
     *
     * @param obj
     * @return
     */
    public static <T> T cloneObj(T obj) {
        T t = null;
        if (obj != null) {
            try {
                if (obj instanceof Integer) {
                    t = (T) (Integer) obj;
                } else if (obj instanceof Long) {
                    t = (T) (Long) obj;
                } else if (obj instanceof Short) {
                    t = (T) (Short) obj;
                } else if (obj instanceof Double) {
                    t = (T) (Double) obj;
                } else if (obj instanceof Float) {
                    t = (T) (Float) obj;
                } else {
                    t = (T) obj.getClass().newInstance();
                    ObjectUtil.copyObjectForDeclaredField(obj, t);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * 克隆集合
     *
     * @param objs
     * @return
     */
    public static <T> List<T> cloneList(List<T> objs) {
        List<T> list = null;
        if (objs != null) {
            list = new ArrayList<>();
            T t = null;
            for (T obj : objs) {
                t = cloneObj(obj);
                if (t != null)
                    list.add(t);
            }
        }
        return list;
    }

    /**
     * 设置父类字段值
     *
     * @param obj
     * @param fieldName
     * @param value
     */
    public static void setSuperField(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取父类字段值
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getSuperField(Object obj, String fieldName) {
        Object object = null;
        try {
            Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            object = field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
