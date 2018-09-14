package com.hex.bigdata.udsp.common.util;

import com.hex.bigdata.udsp.common.constant.ExcelProperty;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by PC on 2017/6/6.
 */
public class ExcelUploadhelper {

    final static String sortNum = "sortNum";

    public static String getCellValue(HSSFSheet sheet, int rowNum, int cellNum) {
        HSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }
        HSSFCell cell = sheet.getRow(rowNum).getCell(cellNum);
        if (cell == null || HSSFCell.CELL_TYPE_BLANK == cell.getCellType()) {
            return "";
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        } else if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
            return Boolean.toString(cell.getBooleanCellValue());
        }
        return cell.getStringCellValue();
    }


    public static Map<String, List> getUploadExcelModel(HSSFSheet sheet, ComUploadExcelContent comUploadExcelContent) throws Exception {
        Map<String, List> resultMap = new HashMap<>(5);
        HSSFRow row = null;
        HSSFCell cell = null;
        Field field;
        if ("UNFIXED".equalsIgnoreCase(comUploadExcelContent.getType())) {
            List<ExcelProperty> properties = comUploadExcelContent.getExcelProperties();
            try {
                Class classModel = Class.forName(comUploadExcelContent.getClassName());
                List excelModels = new ArrayList<>();
                Object result;
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    result = classModel.newInstance();
                    for (ExcelProperty excelProperty : properties) {
                        String name = excelProperty.getName();
                        if (!sortNum.equals(name)) {
                            field = classModel.getDeclaredField(name);
                            field.setAccessible(true);
                            String value = getCellValue(sheet, i, Integer.valueOf(excelProperty.getValue()));
                            if (!value.trim().equals("")) {
                                if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                                    field.set(result, Long.valueOf(value));
                                } else if (field.getType().equals(String.class)) {
                                    field.set(result, value);
                                } else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                                    field.set(result, Short.valueOf(value));
                                } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                                    field.set(result, Integer.valueOf(value));
                                } else {
                                    Class Type = field.getType();
                                    if (Type.isEnum()) {
                                        Enum enumValue = Enum.valueOf(Type, value);
                                        field.set(result, enumValue);
                                    }
                                }
                            }
                        }
                    }
                    excelModels.add(result);
                }
                resultMap.put(comUploadExcelContent.getClassName(), excelModels);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //添加通用内容
            List<ComExcelParam> comExcelParams = comUploadExcelContent.getComExcelParams();
            Class classModel = Class.forName(comUploadExcelContent.getClassName());
            List excelModels = new ArrayList<>();
            Object result = classModel.newInstance();

            for (ComExcelParam comExcelParam : comExcelParams) {
                field = classModel.getDeclaredField(comExcelParam.getName());
                field.setAccessible(true);
                String value = getCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum());
                if (!value.trim().equals("")) {
                    if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                        field.set(result, Long.valueOf(value));
                    } else if (field.getType().equals(String.class)) {
                        field.set(result, value);
                    } else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                        field.set(result, Short.valueOf(value));
                    } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                        field.set(result, Integer.valueOf(value));
                    } else {
                        Class Type = field.getType();
                        if (Type.isEnum()) {
                            Enum enumValue = Enum.valueOf(Type, value);
                            field.set(result, enumValue);
                        }
                    }
                }
            }
            excelModels.add(result);
            resultMap.put(comUploadExcelContent.getClassName(), excelModels);

            //添加配置信息
            List<ComExcelProperties> comExcelPropertiesList = comUploadExcelContent.getComExcelPropertiesList();
            //进行排序
            Collections.sort(comExcelPropertiesList);
            int nowRowNum = 0;
            ComExcelProperties comExcelProperties;
            List comPropertiesList;
            for (int i = 0; i < comExcelPropertiesList.size(); i++) {
                comExcelProperties = comExcelPropertiesList.get(i);
                comPropertiesList = new ArrayList();
                //如果是第一个则初始化开始遍历的行数
                if (i == 0) {
                    nowRowNum = comExcelProperties.getStartRow();
                }
                //boolean canNotLoadNext = false;
                for (int lastRowNum = sheet.getLastRowNum(); nowRowNum <= lastRowNum + 1; nowRowNum++) {
                    row = sheet.getRow(nowRowNum);
                    // Pattern p = Pattern.compile("[0-9a-zA-Z]");
                   /* if(canNotLoadNext){
                        continue;
                    }*/
                    //最后一行或者是遇到第一行内容为下面一个配置的名称
                    if (nowRowNum == lastRowNum + 1 ||
                            (row != null && (i < comExcelPropertiesList.size() - 1)
                                    && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING
                                    && comExcelPropertiesList.get(i + 1).getName().equals(row.getCell(0).getStringCellValue()))) {
                        if (resultMap.containsKey(comExcelProperties.getClassName())) {
                            resultMap.put(comExcelProperties.getClassName() + i, comPropertiesList);
                        } else {
                            resultMap.put(comExcelProperties.getClassName(), comPropertiesList);
                        }
                        //canNotLoadNext = true;
                        nowRowNum = nowRowNum + 2;
                        break;
                    }
                    //空行则直接跳过该次读取数据内容
                    if (row == null || row.getCell(0) == null || HSSFCell.CELL_TYPE_BLANK == row.getCell(0).getCellType()) {

                    } else {
                        classModel = Class.forName(comExcelProperties.getClassName());
                        result = classModel.newInstance();

                        List<ExcelProperty> properties = comExcelProperties.getProperties();
                        for (ExcelProperty excelProperty : properties) {
                            String name = excelProperty.getName();
                            if (!sortNum.equals(name)) {
                                field = classModel.getDeclaredField(name);
                                field.setAccessible(true);
                                String value = getCellValue(sheet, nowRowNum, Integer.valueOf(excelProperty.getValue()));
                                if (!value.trim().equals("")) {
                                    if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                                        field.set(result, Long.valueOf(value));
                                    } else if (field.getType().equals(String.class)) {
                                        field.set(result, value);
                                    } else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                                        field.set(result, Short.valueOf(value));
                                    } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                                        field.set(result, Integer.valueOf(value));
                                    } else {
                                        Class Type = field.getType();
                                        if (Type.isEnum()) {
                                            Enum enumValue = Enum.valueOf(Type, value);
                                            field.set(result, enumValue);
                                        }
                                    }
                                }
                            }
                        }
                        comPropertiesList.add(result);
                    }
                }
            }
        }
        return resultMap;
    }
}
