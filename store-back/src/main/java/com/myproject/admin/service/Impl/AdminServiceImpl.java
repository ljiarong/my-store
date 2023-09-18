package com.myproject.admin.service.Impl;/**
 * ClassName: AdminServiceImpl
 * Package: com.myproject.admin.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myproject.admin.mapper.AdminMapper;
import com.myproject.admin.pojo.AdminUser;
import com.myproject.admin.request.AdminUserRequest;
import com.myproject.admin.service.AdminService;
import com.myproject.clients.CategoryClient;
import com.myproject.clients.ProductClient;
import com.myproject.clients.SearchClient;
import com.myproject.clients.UserClient;
import com.myproject.pojo.Category;
import com.myproject.pojo.Product;
import com.myproject.pojo.User;
import com.myproject.request.PageRequest;
import com.myproject.request.ProductSaveRequest;
import com.myproject.request.ProductSearchRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.MD5Util;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SearchClient searchClient;   //TODO:这里最好把service分开来写，不然太臃肿了

    @Autowired
    private ProductClient productClient;

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

    @Cacheable(key = "#pageRequest.currentPage+'-'+#pageRequest.pageSize",value = "list.category",cacheManager ="cacheManagerHour" )
    @Override
    public R categoryList(PageRequest pageRequest) {
        R r = categoryClient.adminCategoryList(pageRequest);
        log.info("AdminServiceImpl执行结束，结果{categoryList}");
        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R saveCategory(Category category) {
        R r = categoryClient.categorySave(category);
        log.info("AdminServiceImpl执行结束，结果{saveCategory}",r);
        return r;
    }
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R categoryRemove(Integer categoryId) {
        R r = categoryClient.categoryDelete(categoryId);
        log.info("AdminServiceImpl执行结束，结果{categoryRemove}",r);
        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R categoryUpdate(Category category) {
        R r = categoryClient.categoryUpdate(category);
        log.info("AdminServiceImpl执行结束，结果{categoryUpdate}",r);
        return r;
    }

    @Override
    public R productSearch(ProductSearchRequest productSearchRequest) {
        R r = searchClient.searchProduct(productSearchRequest);
        log.info("AdminServiceImpl执行结束，结果{productSearch}",r);
        return r;
    }

    @Override
    public R productSave(ProductSaveRequest productSaveRequest) {
        R r = productClient.productSave(productSaveRequest);
        log.info("AdminServiceImpl执行结束，结果{productSave}",r);
        return r;
    }

    @Override
    public R updateProduct(Product product) {
        R r = productClient.productUpdate(product);
        log.info("AdminServiceImpl执行结束，结果{updateProduct}",r);
        return r;
    }

    @Override
    public R removeProduct(Integer productId) {
        R r = productClient.productRemove(productId);
        log.info("AdminServiceImpl执行结束，结果{removeProduct}",r);
        return r;
    }
}
