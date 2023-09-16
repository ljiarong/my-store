package com.myproject.category.service.Impl;/**
 * ClassName: CategoryServiceImpl
 * Package: com.myproject.category.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myproject.category.mapper.CategoryMapper;
import com.myproject.category.service.CategoryService;
import com.myproject.clients.ProductClient;
import com.myproject.pojo.Category;
import com.myproject.request.CategoryListRequest;
import com.myproject.request.PageRequest;
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
    @Autowired
    ProductClient productClient;
    @Override
    public R getIdByName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrapper=new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",categoryName);
        Category result = categoryMapper.selectOne(categoryQueryWrapper);
        if(result==null){
            log.info("CategoryServiceImpl执行结束，结果{查询失败}");
            return R.fail("类别查询失败");
        }
        log.info("CategoryServiceImpl执行结束，结果{查询成功}");
        return R.ok("查询成功",result);
    }

    @Override
    public R hotsCategory(CategoryListRequest categoryListRequest) {
        QueryWrapper<Category> categoryQueryWrapper=new QueryWrapper<>();
        categoryQueryWrapper.in("category_name",categoryListRequest.getCategoryName());
        categoryQueryWrapper.select("category_id");
        List<Object> ids = categoryMapper.selectObjs(categoryQueryWrapper);
        R ok = R.ok("类别查询成功", ids);
        log.info("CategoryServiceImpl执行结束，结果{查询成功}");

        return ok;
    }

    @Override
    public R getCategoryList() {
        List<Category> categories = categoryMapper.selectList(null);
        R result = R.ok("所有类别数据查询成功", categories);
        log.info("CategoryServiceImpl执行结束，结果{getCategoryList查询成功}");
        return result;
    }

    @Override
    public R categoryListPage(PageRequest pageRequest) {
        IPage<Category> categoryIPage=new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        IPage<Category> result = categoryMapper.selectPage(categoryIPage, null);
        R r = R.ok("查询类别成功", result.getRecords(), result.getTotal());
        return r;
    }

    @Override
    public R saveCategory(Category category) {
        QueryWrapper<Category> categoryQueryWrapper=new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",category.getCategoryName());
        Long aLong = categoryMapper.selectCount(categoryQueryWrapper);
        if (aLong>0) {
            return R.fail("添加失败,类别已经存在");
        }
        int insert = categoryMapper.insert(category);
        log.info("CategoryServiceImpl执行结束，结果{saveCategory}"+insert);
        return R.ok("类别添加成功");
    }

    @Override
    public R categoryDelete(Integer categoryId) {

        Long data = productClient.productCount(categoryId);
        if (data>0) {
            return R.fail("删除失败，有"+data+"件商品在引用");
        }
        int row = categoryMapper.deleteById(categoryId);
        R ok = R.ok("删除成功");
        log.info("CategoryServiceImpl执行结束，结果{categoryDelete删除成功}");
        return ok;

    }

    @Override
    public R categoryUpdate(Category category) {
        QueryWrapper<Category> categoryQueryWrapper=new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",category.getCategoryName());
        Long aLong = categoryMapper.selectCount(categoryQueryWrapper);
        if (aLong>0) {
            return R.fail("修改失败,类别已经存在");
        }
        int insert = categoryMapper.updateById(category);
        log.info("CategoryServiceImpl执行结束，结果{categoryUpdate}",insert);
        return R.ok("类别修改成功");
    }
}
