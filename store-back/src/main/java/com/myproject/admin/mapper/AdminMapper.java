package com.myproject.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myproject.admin.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: AdminMapper
 * Package: com.myproject.admin.mapper
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminUser> {
}
