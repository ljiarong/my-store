package com.myproject.admin.service.Impl;/**
 * ClassName: AdminServiceImpl
 * Package: com.myproject.admin.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.admin.mapper.AdminMapper;
import com.myproject.admin.pojo.AdminUser;
import com.myproject.admin.request.AdminUserRequest;
import com.myproject.admin.service.AdminService;
import com.myproject.clients.UserClient;
import com.myproject.pojo.User;
import com.myproject.request.PageRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.MD5Util;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: my-store
 *
 * @description: 实现类
 *
 * @author: ljr
 *
 * @create: 2023-09-14 22:35
 **/
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserClient userClient;

    @Override
    public AdminUser login(AdminUserRequest adminUserRequest) {
        QueryWrapper<AdminUser> adminUserQueryWrapper=new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_account",adminUserRequest.getUserAccount()).eq("user_password", MD5Util.encode(adminUserRequest.getUserPassword()+MD5Util.USER_SALT));
        AdminUser adminUser = adminMapper.selectOne(adminUserQueryWrapper);
        log.info("AdminServiceImpl执行结束，结果{login}",adminUser);
        return adminUser;
    }

    @Cacheable(key = "#pageRequest.currentPage+'-'+#pageRequest.pageSize",value = "list.user",cacheManager = "cacheManagerHour")  //value是缓存路径,缓存的值的方法返回值
    @Override
    public R userList(PageRequest pageRequest) {
        R r = userClient.userList(pageRequest);
        log.info("AdminServiceImpl执行结束，结果{userList}",r);
        return r;
    }

    @CacheEvict(value = "list.user",allEntries = true)  //缓存清空
    @Override
    public R userRemove(UserIdRequest userId) {
        R r = userClient.userRemove(userId);
        log.info("AdminServiceImpl执行结束，结果{userRemove}",r);
        return r;
    }

    @CacheEvict(value = "list.user",allEntries = true)  //缓存清空
    @Override
    public R userUpdate(User user) {
        R r = userClient.userUpdate(user);
        log.info("AdminServiceImpl执行结束，结果{userUpdate}",r);
        return r;
    }

    @CacheEvict(value = "list.user",allEntries = true)  //缓存清空
    @Override
    public R userSave(User user) {
        R r = userClient.userSave(user);
        log.info("AdminServiceImpl执行结束，结果{userSave}",r);
        return r;
    }
}
