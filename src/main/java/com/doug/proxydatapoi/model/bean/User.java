package com.doug.proxydatapoi.model.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.doug.proxydatapoi.anno.ConversionPropertiesValue;
import com.doug.proxydatapoi.anno.DefaultPrimaryKey;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Date: 2021/9/10 11:04<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */

@Data
@Table(name = "user")
public class User {

    @Id
    @DefaultPrimaryKey
    private String id;

    @Excel(name="性别")
    @ConversionPropertiesValue
    private String sex;

    @Excel(name="姓名")
    private String name;
}
