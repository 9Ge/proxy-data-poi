package com.doug.proxydatapoi.service;

import com.doug.proxydatapoi.anno.ConversionPropertiesValue;
import com.doug.proxydatapoi.anno.DefaultPrimaryKey;
import com.doug.proxydatapoi.mapper.DictMapper;
import com.doug.proxydatapoi.model.bean.Dict;
import com.doug.proxydatapoi.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * 自定义数据功能
 *
 * @Date: 2021/9/8 16:34<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Service
@Slf4j
public class BusinessDataService {

    @Autowired(required = false)
    private DictMapper dictMapper;

    public void saveBusiness(Object object, Field[] declaredFields) throws IllegalAccessException {
        log.info("{}", object);
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            if (!this.defaultKey(object, field)) {
                continue;
            }
            if (!this.conversion(object, field, false)) {
                continue;
            }
        }
    }

    public void queryBusiness(Object object, Field[] declaredFields) throws IllegalAccessException {
        log.info("{}", object);
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            if (!this.conversion(object, field, true)) {
                continue;
            }
        }
    }

    /**
     * 设置主键的默认值
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public boolean defaultKey(Object object, Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(DefaultPrimaryKey.class)) {
            field.setAccessible(true);
            String primaryKey = (String) field.get(object);
            if (StringUtils.isEmpty(primaryKey)) {
                field.set(object, UUIDUtil.uuid());
            }
            return false;
        }
        return true;
    }


    /**
     * 转化数据
     *
     * @param object
     * @param field
     * @param flag   true获取name[get时]  false获取code[save时]
     * @return
     * @throws IllegalAccessException
     */
    public boolean conversion(Object object, Field field, boolean flag) throws IllegalAccessException {
        if (field.isAnnotationPresent(ConversionPropertiesValue.class)) {
            field.setAccessible(true);
            String data = String.valueOf(field.get(object));
            if (StringUtils.isNotEmpty(data)) {
                String obj = conversionData(data, flag);
                if(StringUtils.isNotEmpty(obj)){
                    field.set(object,obj);
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 转换数据
     * 将code和name 互相转化
     *
     * @param data
     * @param flag true 获取name  false 获取code
     */
    public String conversionData(String data, boolean flag) {
        try {
            Dict dict = new Dict();
            if (flag) {
                dict.setCode(Integer.valueOf(data));
            } else {
                dict.setName(data);
            }
            return dictMapper.queryData(dict);
        } catch (NullPointerException e) {
            log.info("转化异常：{}", e);
            return null;
        }
    }


}
