package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.dao.ComDatasourceMapper;
import com.hex.bigdata.udsp.common.dao.ComPropertiesMapper;
import com.hex.bigdata.udsp.common.model.*;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.im.dao.*;
import com.hex.bigdata.udsp.im.dto.ImModelView;
import com.hex.bigdata.udsp.im.model.*;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 交互建模处理类
 * Created by jintian on 2017/9/6.
 */
@Service
public class ImModelService {

    @Autowired
    private ImModelMapper imModelMapper;

    @Autowired
    private ImModelFilterColMapper imModelFilterColMapper;

    @Autowired
    private ImModelMappingMapper imModelMappingMapper;

    @Autowired
    private ComPropertiesMapper comPropertiesMapper;

    @Autowired
    private ImModelUpdateKeyMapper imModelUpdateKeyMapper;

    @Autowired
    private ImProviderService imProviderService;

    @Autowired
    private ImMetadataMapper imMetadataMapper;

    @Autowired
    private ComDatasourceMapper comDatasourceMapper;

    @Autowired
    private GFDictMapper gfDictMapper;

    @Autowired
    private ImMetadataColMapper imMetadataColMapper;

    private static List<ComExcelParam> comExcelParams;

    static{
        comExcelParams = new ArrayList<>();
        comExcelParams.add(new ComExcelParam(1, 1, "name"));
        comExcelParams.add(new ComExcelParam(1, 3, "sDsId"));
        comExcelParams.add(new ComExcelParam(2, 1, "describe"));
        comExcelParams.add(new ComExcelParam(2, 3, "tMdId"));
        comExcelParams.add(new ComExcelParam(3, 1, "note"));
        comExcelParams.add(new ComExcelParam(5, 1, "updateMode"));
        comExcelParams.add(new ComExcelParam(5, 3, "updateKey"));
        comExcelParams.add(new ComExcelParam(6, 1, "buildMode"));
        comExcelParams.add(new ComExcelParam(6, 3, "eDsId"));
    }

    @Transactional
    public String insert(ImModelViews imModelViews) throws Exception{
        String pkId = Util.uuid();
        ImModel model = imModelViews.getImModel();
        model.setPkId(pkId);
        if (!imModelMapper.insert(pkId, model)) {
            throw new RuntimeException("保存配置栏基础信息异常");
        }
        if(imModelViews.getImModelFilterCols() != null && !imModelFilterColMapper.insertFilterCols(pkId,imModelViews.getImModelFilterCols())){
            throw new RuntimeException("保存过滤字段异常");
        }
        if(imModelViews.getImModelMappings() != null && !imModelMappingMapper.insertModelMappings(pkId,imModelViews.getImModelMappings())){
            throw new RuntimeException("保存映射字段异常");
        }
        if(imModelViews.getComPropertiesList() != null && !comPropertiesMapper.insertModelComProperties(pkId,imModelViews.getComPropertiesList())){
            throw new RuntimeException("保存参数配置栏信息异常");
        }
        //添加更新字段
        String updateKeys = model.getUpdateKey();
        if(StringUtils.isNotBlank(updateKeys)){
            String[] updateKeyArray = updateKeys.split(",");
            String updateKeyId;
            for(String updateKey : updateKeyArray){
                ImModelUpdateKey imModelUpdateKey = new ImModelUpdateKey();
                updateKeyId = Util.uuid();
                //设置插入的ID
                imModelUpdateKey.setPkId(updateKeyId);
                imModelUpdateKey.setModelId(pkId);
                imModelUpdateKey.setColId(updateKey);
                if(!imModelUpdateKeyMapper.insert(updateKeyId,imModelUpdateKey)){
                    throw new RuntimeException("添加更新主键异常");
                }
            }
        }
        return pkId;
    }

    @Transactional
    public boolean update(ImModelViews imModelViews) throws Exception{
        ImModel model = imModelViews.getImModel();
        //获取模型主键
        String pkId = model.getPkId();
        if (!imModelMapper.update(pkId, model)) {
            //添加运行时异常，使事务回滚
            throw new RuntimeException("更新配置栏基础信息异常");
        }

        if(!imModelFilterColMapper.deleteList(pkId) || !imModelFilterColMapper.insertFilterCols(pkId,imModelViews.getImModelFilterCols())){
            throw new RuntimeException("更新过滤字段异常");
        }

        if(!imModelMappingMapper.deleteList(pkId) || !imModelMappingMapper.insertModelMappings(pkId,imModelViews.getImModelMappings())){
            throw new RuntimeException("更新映射字段异常");
        }

        if(!comPropertiesMapper.deleteList(pkId) || !comPropertiesMapper.insertModelComProperties(pkId,imModelViews.getComPropertiesList())){
            throw new RuntimeException("更新参数配置栏信息异常");
        }

        if(!imModelUpdateKeyMapper.deleteList(pkId)){
            throw new RuntimeException("更新更新主键异常1");
        }
        //添加更新字段
        String updateKeys = model.getUpdateKey();
        if(StringUtils.isNotBlank(updateKeys)){
            String[] updateKeyArray = updateKeys.split(",");
            String updateKeyId;
            for(String updateKey : updateKeyArray){
                ImModelUpdateKey imModelUpdateKey = new ImModelUpdateKey();
                updateKeyId = Util.uuid();
                //设置插入的ID
                imModelUpdateKey.setPkId(updateKeyId);
                imModelUpdateKey.setModelId(pkId);
                imModelUpdateKey.setColId(updateKey);
                if(!imModelUpdateKeyMapper.insert(updateKeyId,imModelUpdateKey)){
                    throw new RuntimeException("更新更新主键异常2");
                }
            }
        }
        return true;
    }

    public List<ImModelView> selectPage(ImModelView imModelView, Page page) {

        return imModelMapper.selectPage(imModelView,page);
    }

    public boolean delete(ImModel[] imModels) throws Exception {

        for(ImModel imModel : imModels){
            imModel.setDelFlg("1");
            // 进行逻辑删除
            if(!imModelMapper.updateStatus(imModel.getPkId(),"1")){
                //跑出异常，进行回滚
                throw new RuntimeException("删除失败,删除失败的模型名称为：" + imModel.getName());
            }
        }
        return true;
    }

    public ImModel selectByPkId(String pkId) {
        return imModelMapper.select(pkId);
    }

    public ImModel selectByName(String modelName) {
        return imModelMapper.selectByName(modelName);
    }

    public List<MetadataCol> getSrcMateData(Property[] properties, String srcDataSourceId) {
        //根据元数据获取相关信息
        Model model = new Model(Arrays.asList(properties));
        ComDatasource comDatasource= comDatasourceMapper.select(srcDataSourceId);
        List<ComProperties> comProperties = comPropertiesMapper.selectByFkId(srcDataSourceId);
        Datasource datasource = new Datasource(comDatasource,comProperties);
        // 由于该实现类和模型中的实现类不一样故制为空
        datasource.setImplClass("");
        model.setProperties(Arrays.asList(properties));
        model.setSourceDatasource(datasource);
        return imProviderService.getCloumnInfo(model);
    }

    @Transactional
    public Map<String,String> uploadExcel(String uploadFilePath) {
        Map resultMap = new HashMap<String, String>(2);
        File uploadFile = new File(uploadFilePath);
        FileInputStream in = null;
        int i = 0;
        try {
            ComUploadExcelContent dataSourceContent = new ComUploadExcelContent();
            dataSourceContent.setClassName("com.hex.bigdata.udsp.im.model.ImModel");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            comExcelPropertiesList.add(new ComExcelProperties("配置栏", "com.hex.bigdata.udsp.common.model.ComProperties", 12, 0, 1, ComExcelEnums.Comproperties.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("字段映射", "com.hex.bigdata.udsp.im.model.ImModelMapping", 10, 0, 2, ComExcelEnums.ImMapping.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("字段过滤", "com.hex.bigdata.udsp.im.model.ImModelFilterCol", 10, 0, 3, ComExcelEnums.ImModelFiterCol.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;

            Page page = new Page();
            page.setPageIndex(0);
            page.setPageSize(1);
            for (int activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                ImModelViews imModelView = new ImModelViews();
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<ImModel> imModels = (List<ImModel>) uploadExcelModel.get("com.hex.bigdata.udsp.im.model.ImModel");
                ImModel imModel = imModels.get(0);
                if (imModelMapper.selectByName(imModel.getName()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个名称已存在！");
                    break;
                }
                /*//更改模型源信息  单独获取模块名称
                String modelName = ExcelUploadhelper.getCellValue(sheet,7,1);*/
                imModel.setsDsId(comDatasourceMapper.selectByModelAndName("IM",imModel.getsDsId()).getPkId());
                //更改目标元数据
                imModel.settMdId(imMetadataMapper.selectByName(imModel.gettMdId()).getPkId());
                //更改引擎数据源
                imModel.seteDsId(comDatasourceMapper.selectByModelAndName("IM",imModel.geteDsId()).getPkId());

                //更改更新策略
                if(StringUtils.isNotBlank(imModel.getUpdateMode())){
                    imModel.setUpdateMode(imModelMapper.getModelUpdateModeByName(imModel.getUpdateMode()));
                }

                //更改构建策略
                if(StringUtils.isNotBlank(imModel.getBuildMode())){
                    imModel.setBuildMode(imModelMapper.getModelBuildModeByName(imModel.getBuildMode()));
                }

                //设置模型类型
                if(comDatasourceMapper.checkSourceType(imModel.getsDsId())){
                    imModel.setType("2");
                }else{
                    imModel.setType("1");
                }

                //设置模型状态
                imModel.setStatus("1");
                //设置模型
                imModelView.setImModel(imModel);
                //获取参数内容
                List<ComProperties> comPropertiesList = (List<ComProperties>) uploadExcelModel.get("com.hex.bigdata.udsp.common.model.ComProperties");
                imModelView.setComPropertiesList(comPropertiesList);
                //获取映射内容
                List<ImModelMapping> imModelMappings = (List<ImModelMapping>) uploadExcelModel.get("com.hex.bigdata.udsp.im.model.ImModelMapping");
                    //遍历更改目标字段对应的字段pkId
                if(imModelMappings != null){
                    for(ImModelMapping imModelMapping : imModelMappings){
                        imModelMapping.setPkId(imMetadataColMapper.selectByNameAndMdId(imModel.gettMdId(),imModelMapping.getColId()).getPkId());
                    }
                }
                imModelView.setImModelMappings(imModelMappings);
                //获取过滤字段内容
                List<ImModelFilterCol> imModelFilterCols = (List<ImModelFilterCol>) uploadExcelModel.get("com.hex.bigdata.udsp.im.model.ImModelFilterCol");
                imModelView.setImModelFilterCols(imModelFilterCols);
                String pkId = insert(imModelView);

                //更改主键
                String updateKey = imModel.getUpdateKey();
                if(StringUtils.isNotBlank(updateKey)){
                    String[] updateKeyNames = updateKey.split(",");

                    String updateKeyPkId;
                    ImModelUpdateKey imModelUpdateKey = new ImModelUpdateKey();
                    imModelUpdateKey.setModelId(pkId);
                    for(String updateKeyName : updateKeyNames){
                        updateKeyPkId = Util.uuid();
                        imModelUpdateKey.setModelId(updateKeyPkId);
                        imModelUpdateKey.setColId(imMetadataColMapper.selectByNameAndMdId(imModel.gettMdId(),updateKeyName).getPkId());
                        imModelUpdateKeyMapper.insert(updateKeyPkId,imModelUpdateKey);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status", "false");
            resultMap.put("message","第"+i+"个报错异常,错误信息：" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    public String createExcel(ImModel[] imModels) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        // 模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_imModel.xls";
        // 下载地址
        String dirPath = CreateFileUtil.getLocalDirPath();
        dirPath += seprator + "download_imModel_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
        // 获取模板文件第一个Sheet对象
        POIFSFileSystem sourceFile = null;

        try {
            sourceFile = new POIFSFileSystem(new FileInputStream(
                    templateFile));

            sourceWork = new HSSFWorkbook(sourceFile);
            sourceSheet = sourceWork.getSheetAt(0);
            //创建表格
            workbook = new HSSFWorkbook();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet;
        for (ImModel imModel : imModels) {
            sheet = workbook.createSheet();

            //将前面样式内容复制到下载表中
            int i = 0;
            for (; i < 12; i++) {
                try {
                    ExcelCopyUtils.copyRow(sheet.createRow(i), sourceSheet.getRow(i), sheet.createDrawingPatriarch(), workbook);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //设置内容
            imModel = imModelMapper.select(imModel.getPkId());
            //设置引擎数据源
            if(StringUtils.isNotBlank(imModel.geteDsId())){
                imModel.seteDsId(comDatasourceMapper.select(imModel.geteDsId()).getName());
            }
            //设置源数据名称
            ComDatasource comDatasource = comDatasourceMapper.select(imModel.getsDsId());
            imModel.setsDsId(comDatasource.getName());
            /*//设置源数据模块名称 单独设置
            ExcelCopyUtils.setCellValue(sheet,7,7,comDatasource.getModel());*/

            //设置目标元数据名称
            imModel.settMdId(imMetadataMapper.select(imModel.gettMdId()).getName());
            //设置更新策略,不为空时更新
            if(StringUtils.isNotBlank(imModel.getUpdateMode())){
                imModel.setUpdateMode(gfDictMapper.selectByPrimaryKey("IM_MODEL_UPDATE_TYPE",imModel.getUpdateMode()).getDictName());
            }
            //设置构建策略
            if(StringUtils.isNotBlank(imModel.getBuildMode())){
                imModel.setBuildMode(gfDictMapper.selectByPrimaryKey("IM_MODEL_BUILD_TYPE",imModel.getBuildMode()).getDictName());
            }
            //设置更新主键
            List<ImMetadataCol> imMetadataCols = imMetadataColMapper.selectModelUpdateKeys(imModel.getPkId());
            StringBuffer updateKey = new StringBuffer("");
            for(int k = 0,len = imMetadataCols.size(); k < len ;k++){
                updateKey.append(imMetadataCols.get(k).getName());
                //不为最后一个时
                if(k != len-1){
                    updateKey.append(",");
                }
            }
            imModel.setUpdateKey(updateKey.toString());

            for (ComExcelParam comExcelParam : comExcelParams) {
                try {
                    Field field = imModel.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(imModel) == null ? "" : field.get(imModel).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //设置参数栏
            List<ComProperties> comProperties = comPropertiesMapper.selectByFkId(imModel.getPkId());
            if (comProperties.size() > 0) {
                int k = 1;
                for (ComProperties comProperty : comProperties) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(k);
                    cell = row.createCell(1);
                    cell.setCellValue(comProperty.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(comProperty.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(comProperty.getValue());
                    i++;
                    k++;
                }
            }

            //设置字段映射栏
            try {
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(16), sheet.createDrawingPatriarch(), workbook);
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(17), sheet.createDrawingPatriarch(), workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<ImModelMapping> imModelMappings = imModelMappingMapper.selectByModelId(imModel.getPkId());
            if (imModelMappings.size() > 0) {
                for (ImModelMapping imModelMapping : imModelMappings) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(imModelMapping.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(imModelMapping.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(imModelMapping.getColId());
                    cell = row.createCell(3);
                    cell.setCellValue(imModelMapping.getType());
                    cell = row.createCell(4);
                    cell.setCellValue(imModelMapping.getLength());
                    cell = row.createCell(5);
                    cell.setCellValue(imModelMapping.getDescribe());
                    cell = row.createCell(6);
                    cell.setCellValue(imModelMapping.getNote());
                    i++;
                }
            }

            //设置字段过滤
            try {
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(21), sheet.createDrawingPatriarch(), workbook);
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(22), sheet.createDrawingPatriarch(), workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<ImModelFilterCol> imModelFilterCols = imModelFilterColMapper.selectList(imModel.getPkId());
            if (imModelFilterCols.size() > 0) {
                for (ImModelFilterCol imModelFilterCol : imModelFilterCols) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(imModelFilterCol.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(imModelFilterCol.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(imModelFilterCol.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(imModelFilterCol.getType());
                    cell = row.createCell(4);
                    cell.setCellValue(imModelFilterCol.getLength());
                    cell = row.createCell(5);
                    cell.setCellValue(imModelFilterCol.getIsNeed().equals("1")?"否":"是");
                    cell = row.createCell(6);
                    cell.setCellValue(imModelFilterCol.getOperator());
                    cell = row.createCell(7);
                    cell.setCellValue(imModelFilterCol.getDefaultVal());
                    cell = row.createCell(8);
                    cell.setCellValue(imModelFilterCol.getLabel());
                    i++;
                }
            }
        }
        if (workbook != null) {
            try {
                FileOutputStream stream = new FileOutputStream(dirPath);
                workbook.write(new FileOutputStream(dirPath));
                stream.close();
                return dirPath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
