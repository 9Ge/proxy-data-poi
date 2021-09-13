package com.doug.proxydatapoi.model.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Date: 2021/9/10 11:36<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@Table(name= "dict")
public class Dict {
    @Id
    @Column(name = "id")
    private String id;
    private Integer code;
    private String name;

    public Dict() {
    }

    public Dict(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
