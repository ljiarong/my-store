package com.myproject.admin.service;

import com.myproject.admin.pojo.AdminUser;
import com.myproject.admin.request.AdminUserRequest;
import com.myproject.pojo.Category;
import com.myproject.pojo.Product;
import com.myproject.pojo.User;
import com.myproject.request.PageRequest;
import com.myproject.request.ProductSaveRequest;
import com.myproject.request.ProductSearchRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;

/**
 * ClassName: AdminService
 * Package: com.myproject.admin.service
 */
public interface AdminService {
    AdminUser login(AdminUserRequest adminUserRequest);

    R userList(PageRequest pageRequest);

    R userRemove(UserIdRequest userId);

    R userUpdate(User user);

    R userSave(User user);

    R categoryList(PageRequest pageRequest);

    R saveCategory(Category category);

    R categoryRemove(Integer categoryId);

    R categoryUpdate(Category category);

    R productSearch(ProductSearchRequest productSearchRequest);

    R productSave(ProductSaveRequest productSaveRequest);

    R updateProduct(Product product);

    R removeProduct(Integer productId);
}
