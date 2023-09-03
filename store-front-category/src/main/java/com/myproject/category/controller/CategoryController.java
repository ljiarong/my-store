package com.myproject.category.controller;/**
 * ClassName: CategoryController
 * Package: com.myproject.category.controller
 */

import com.myproject.category.service.CategoryService;
import com.myproject.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description: 类别控制器
 *
 * @author: ljr
 *
 * @create: 2023-07-21 14:47
 *
 **/
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R getIdByname(@PathVariable String categoryName){
        if(StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为空，查询失败");
        }
        return categoryService.getIdByName(categoryName);
    }
}
