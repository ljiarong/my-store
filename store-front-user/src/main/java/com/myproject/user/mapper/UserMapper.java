package com.myproject.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myproject.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserMapper
 * Package: com.myproject.user.mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
