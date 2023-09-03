package com.myproject.product.service.Impl;/**
 * ClassName: ProductServiceImpl
 * Package: com.myproject.product.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myproject.clients.CategoryClient;
import com.myproject.pojo.Category;
import com.myproject.pojo.Product;
import com.myproject.product.mapper.ProductMapper;
import com.myproject.product.service.ProductService;
import com.myproject.request.CategoryNameRequest;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-07-21 15:49
 **/
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private ProductMapper productMapper;
    /**
    * @Author: ljr
    * @Description: 查询类别商品
    * @DateTime: 2023/7/21
    * @Params: 
    * @Return 
    */
    @Override
    public R promo(String categoryName) {
        R result=categoryClient.getIdByName(categoryName);
        if (result.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl执行结束，结果{类别查询失败}");
            return result;
        }
        LinkedHashMap<String,Object> categoryResult=(LinkedHashMap<String, Object>) result.getData();
        Integer categoryId=(Integer) categoryResult.get("categoryId");
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.eq("category_id",categoryId).orderByDesc("product_sales");
        IPage<Product> productIPage=new Page<>(1,7);
        productIPage=productMapper.selectPage(productIPage,productQueryWrapper);

        List<Product> productList=productIPage.getRecords();  //当前页的数据
        long total=productIPage.getTotal();//获取总条数

        log.info("ProductServiceImpl执行结束，结果{查询成功}");
        return R.ok("查询成功",productIPage);
    }
}
