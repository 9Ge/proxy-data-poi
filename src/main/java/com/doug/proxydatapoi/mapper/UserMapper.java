package com.doug.proxydatapoi.mapper;

import com.doug.proxydatapoi.model.bean.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Date: 2021/9/10 11:30<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Repository
public interface UserMapper extends Mapper<User> {
}
