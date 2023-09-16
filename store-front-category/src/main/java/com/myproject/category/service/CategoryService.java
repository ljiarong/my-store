package com.myproject.category.service;

import com.myproject.pojo.Category;
import com.myproject.request.CategoryListRequest;
import com.myproject.request.PageRequest;
import com.myproject.utils.R;

/**
 * ClassName: CategoryService
 * Package: com.myproject.category.service
 */
public interface CategoryService {


    R getIdByName(String categoryName);

    /**
    * @Author: ljr
    * @Description: 传入类别名称集合，返回类别id集合
    * @DateTime: 2023/9/4
    * @Params: 
    * @Return 
    */
    R hotsCategory(CategoryListRequest categoryListRequest);

    R getCategoryList();

    R categoryListPage(PageRequest pageRequest);

    R saveCategory(Category category);

    R categoryDelete(Integer categoryId);

    R categoryUpdate(Category category);
}
