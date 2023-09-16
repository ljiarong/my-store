package com.myproject.admin.controller;/**
 * ClassName: CategoryController
 * Package: com.myproject.admin.controller
 */

import com.myproject.admin.service.AdminService;
import com.myproject.pojo.Category;
import com.myproject.request.PageRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-16 22:44
 **/
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private AdminService adminService;

    @GetMapping("list")
    public R categoryList(PageRequest pageRequest){
        return adminService.categoryList(pageRequest);
    }

    @PostMapping("save")
    public R categorySave(Category category){
        return adminService.saveCategory(category);
    }
    @PostMapping("remove")
    public R categoryRemove(Integer categoryId){return adminService.categoryRemove(categoryId);}

    @PostMapping("update")
    public R categoryUpdate(Category category){
        return adminService.categoryUpdate(category);
    }
}
