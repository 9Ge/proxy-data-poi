package com.doug.proxydatapoi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.doug.proxydatapoi.constant.ExcelTableEnum;
import com.doug.proxydatapoi.handle.SaveExcelDataHandle;
import com.doug.proxydatapoi.mapper.UserMapper;
import com.doug.proxydatapoi.model.bean.User;
import com.doug.proxydatapoi.model.vo.ExcelPropertiesVo;
import com.doug.proxydatapoi.util.FileWithExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Date: 2021/9/7 14:20<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/A99Excel")
public class ExcelController {

    @Autowired
    private SaveExcelDataHandle saveExcelDataHandle;

    @RequestMapping(value = "/import/{tableName}", method = RequestMethod.POST)
    public String importExcel( @RequestParam(value = "file", required = true) MultipartFile file,
                        @PathVariable(name = "tableName") String tableName) {
        ExcelPropertiesVo excelPropertiesVo = ExcelTableEnum.getExcelPropertiesByEnName(tableName);
        if( excelPropertiesVo== null){
            return "当前表枚举中不存在，请联系管理员添加";
        }
        Class beanClazz = excelPropertiesVo.getBeanClazz();
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedSave(true);
        List<Object> excelList = null;
        try {
            excelList = ExcelImportUtil.importExcel(file.getInputStream(), beanClazz, params);
        } catch (NumberFormatException e) {
            return e.getMessage().replace("For input string: ", "") + "请求改成数字";
        } catch (Exception e) {
            log.info("excel转化失败，{}", e);
            return "转换Excel失败,请联系管理员!";
        }
        log.info("Excel中的数据{}", excelList);
        Class mapperClazz = excelPropertiesVo.getMapperClazz();
        saveExcelDataHandle.saveExcelData(mapperClazz, excelList);
        return "导入完成";
    }


    @RequestMapping(value = "/export/{tableName}", method = RequestMethod.GET)
    public void exportExcel(@PathVariable(name = "tableName") String tableName, HttpServletResponse response) {
        ExcelPropertiesVo excelPropertiesVo = ExcelTableEnum.getExcelPropertiesByEnName(tableName);
        if( excelPropertiesVo== null){
            throw new RuntimeException("当前表枚举中不存在，请联系管理员添加") ;
        }
        Class mapperClazz = excelPropertiesVo.getMapperClazz();
        Class beanClazz = excelPropertiesVo.getBeanClazz();
        String tableChName = excelPropertiesVo.getTableChName();
        List list = saveExcelDataHandle.queryExcelData(mapperClazz, beanClazz);
        log.info("导出的数据{}", list);
        FileWithExcelUtil.exportExcel(list,tableChName,tableChName,beanClazz,tableChName+".xls",response);
    }



}
