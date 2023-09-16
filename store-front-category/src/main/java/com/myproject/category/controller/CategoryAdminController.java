package com.myproject.category.controller;/**
 * ClassName: CategoryAdminController
 * Package: com.myproject.category.controller
 */

import com.myproject.category.service.CategoryService;
import com.myproject.pojo.Category;
import com.myproject.request.CategoryNameRequest;
import com.myproject.request.PageRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-16 23:39
 **/
@RestController
@RequestMapping("admin/category")
public class CategoryAdminController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("list")
    public R categoryListPage(@RequestBody PageRequest pageRequest){
        return categoryService.categoryListPage(pageRequest);
    }

    @PostMapping("save")
    public R categorySave(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @PostMapping("delete")
    public R categoryDelete(@RequestBody Integer categoryId){
        return  categoryService.categoryDelete(categoryId);
    }

    @PostMapping("update")
    public R categoryUpdate(@RequestBody Category category){
        return categoryService.categoryUpdate(category);
    }


}
