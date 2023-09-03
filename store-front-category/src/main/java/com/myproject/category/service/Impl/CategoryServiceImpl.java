package com.myproject.category.service.Impl;/**
 * ClassName: CategoryServiceImpl
 * Package: com.myproject.category.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.category.mapper.CategoryMapper;
import com.myproject.category.service.CategoryService;
import com.myproject.pojo.Category;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-07-21 15:20
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public R getIdByName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrappery=new QueryWrapper<>();
        categoryQueryWrappery.eq("category_name",categoryName);
        Category result = categoryMapper.selectOne(categoryQueryWrappery);
        if(result==null){
            log.info("CategoryServiceImpl执行结束，结果{查询失败}");
            return R.fail("类别查询失败");
        }
        log.info("CategoryServiceImpl执行结束，结果{查询成功}");
        return R.ok("查询成功",result);
    }
}
