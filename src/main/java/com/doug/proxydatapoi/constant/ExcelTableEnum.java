package com.doug.proxydatapoi.constant;


import com.doug.proxydatapoi.mapper.UserMapper;
import com.doug.proxydatapoi.model.bean.User;
import com.doug.proxydatapoi.model.vo.ExcelPropertiesVo;

/**
 * @Date: 2021/9/7 14:19<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public enum ExcelTableEnum {
   A01EquipmentAssetsBean(User.class, UserMapper.class, "user","用户信息"),
    ;
    private Class beanClazz;
    private Class  mapperClazz;
    private String tableEnName;
    private String tableChName;

    ExcelTableEnum(Class beanClazz, Class mapperClazz, String tableEnName, String tableChName) {
        this.beanClazz = beanClazz;
        this.mapperClazz = mapperClazz;
        this.tableEnName = tableEnName;
        this.tableChName = tableChName;
    }



    public static ExcelPropertiesVo getExcelPropertiesByEnName(String tableName) {
        ExcelTableEnum[] values = ExcelTableEnum.values();
        for (int i = 0; i < values.length; i++) {
            ExcelTableEnum value = values[i];
            if (tableName.equals(value.tableEnName)) {
                return new ExcelPropertiesVo(value.beanClazz,value.mapperClazz,value.tableEnName,value.tableChName);
            }
        }
        return null;
    }


}
