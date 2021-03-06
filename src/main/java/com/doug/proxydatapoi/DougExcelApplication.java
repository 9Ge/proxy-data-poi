package com.doug.proxydatapoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages ="com.doug.proxydatapoi.mapper")
public class DougExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(DougExcelApplication.class, args);
    }

}
