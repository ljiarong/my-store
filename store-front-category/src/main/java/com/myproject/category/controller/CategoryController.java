package com.myproject.category.controller;/**
 * ClassName: CategoryController
 * Package: com.myproject.category.controller
 */

import com.myproject.category.service.CategoryService;
import com.myproject.request.CategoryListRequest;
import com.myproject.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated CategoryListRequest categoryListRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("类别集合查询失败");
        }
        return categoryService.hotsCategory(categoryListRequest);
    }

    @GetMapping("list")
    public R categoryList(){
        return categoryService.getCategoryList();
    }
}
