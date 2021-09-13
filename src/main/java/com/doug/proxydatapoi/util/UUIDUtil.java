package com.doug.proxydatapoi.util;

import java.util.UUID;

/**
 * @Date: 2021/9/10 11:00<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
