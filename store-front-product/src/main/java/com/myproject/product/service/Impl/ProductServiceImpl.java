package com.myproject.product.service.Impl;/**
 * ClassName: ProductServiceImpl
 * Package: com.myproject.product.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myproject.clients.CategoryClient;
import com.myproject.clients.ProductClient;
import com.myproject.clients.SearchClient;
import com.myproject.pojo.Category;
import com.myproject.pojo.Picture;
import com.myproject.pojo.Product;
import com.myproject.product.mapper.PictureMapper;
import com.myproject.product.mapper.ProductMapper;
import com.myproject.product.service.ProductService;
import com.myproject.request.*;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    private SearchClient searchClient;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;
    /**
    * @Author: ljr
    * @Description: 查询类别商品
    * @DateTime: 2023/7/21
    * @Params: 
    * @Return 
    */
    @Cacheable(value = "list.product",key = "#categoryName",cacheManager = "cacheManagerHour")
    @Override
    public R promo(String categoryName) {
        R result=categoryClient.getIdByName(categoryName);
        if (result.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl执行结束，结果{类别查询失败}");
            return result;
        }
        LinkedHashMap<String,Object> categoryResult=(LinkedHashMap<String, Object>) result.getData();
        Integer categoryId=(Integer) categoryResult.get("category_id");
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.eq("category_id",categoryId).orderByDesc("product_sales");
        IPage<Product> productIPage=new Page<>(1,7);
        productIPage=productMapper.selectPage(productIPage,productQueryWrapper);

        List<Product> productList=productIPage.getRecords();  //当前页的数据
        long total=productIPage.getTotal();//获取总条数

        log.info("ProductServiceImpl执行结束，结果{查询成功}");
        return R.ok("查询成功",productList);
    }

    @Cacheable(value = "list.product",key = "#categoryListRequest.categoryName")
    @Override
    public R hots(CategoryListRequest categoryListRequest) {
        R r = categoryClient.hots(categoryListRequest);
        if(r.getCode().equals(R.FAIL_CODE)){
            log.info("ProductServiceImpl执行结束，结果{查询失败}");
            return r;
        }
        List<Object> ids = (List<Object>) r.getData();
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.in("category_id",ids).orderByDesc("product_sales");
        IPage<Product> productIPage=new Page<>(1,7);
        productIPage=productMapper.selectPage(productIPage,productQueryWrapper);
        List<Product> results=productIPage.getRecords();
        R ok = R.ok("多类别热门商品查询成功", results);

        return ok;
    }

    @Cacheable(value = "list.category",key = "#root.methodName",cacheManager = "cacheManagerDay")
    @Override
    public R clist() {
        R result=categoryClient.list();
        log.info("ProductServiceImpl执行结束，结果{clist}",result);
        return result;
    }

    @Cacheable(value = "list.product",key = "#categoryIdList.categoryID+'-'+#categoryIdList.currentPage+'-'+#categoryIdList.pageSize")
    @Override
    public R getProductByCategoryId(CategoryIdList categoryIdList) {
        List<Integer> categoryID = categoryIdList.getCategoryID();

        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        if (!categoryID.isEmpty()) {
            productQueryWrapper.in("category_id",categoryID).orderByDesc("product_sales");

        }
        IPage<Product> productIPage=new Page<>(categoryIdList.getCurrentPage(), categoryIdList.getPageSize());
        productIPage=productMapper.selectPage(productIPage,productQueryWrapper);
        R ok = R.ok("查询成功", productIPage.getRecords(), productIPage.getTotal());
        return ok;
    }

    @Cacheable(value = "product",key = "#productID")
    @Override
    public R detailById(Integer productID) {
        Product product = productMapper.selectById(productID);
        R ok = R.ok("查询成功", product);
        log.info("ProductServiceImpl执行结束，结果{detailById}",ok);
        return ok;
    }

    @Cacheable(value = "picture",key = "#productID")
    @Override
    public R pictureById(Integer productID) {
        QueryWrapper<Picture> pictureQueryWrapper=new QueryWrapper<>();
        pictureQueryWrapper.eq("product_id",productID);
        List<Picture> pictures = pictureMapper.selectList(pictureQueryWrapper);
        R ok = R.ok("查询成功", pictures);
        log.info("ProductServiceImpl执行结束，结果{pictureById查询成功}",ok);
        return ok;
    }



    @Override
    public List<Product> getAllProduct() {
        List<Product> result = productMapper.selectList(null);
        log.info("ProductServiceImpl执行结束，结果{getAllProduct}",result);
        return result;
    }

    @Override
    public R searchProduct(ProductSearchRequest productSearchRequest) {
        log.info("ProductServiceImpl执行结束，结果{searchProduct}");
        return searchClient.searchProduct(productSearchRequest);
    }
}
