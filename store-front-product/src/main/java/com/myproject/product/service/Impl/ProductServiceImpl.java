package com.myproject.product.service.Impl;/**
 * ClassName: ProductServiceImpl
 * Package: com.myproject.product.service.Impl
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myproject.clients.*;
import com.myproject.message.OrderToProduct;
import com.myproject.pojo.Category;
import com.myproject.pojo.Collect;
import com.myproject.pojo.Picture;
import com.myproject.pojo.Product;
import com.myproject.product.mapper.PictureMapper;
import com.myproject.product.mapper.ProductMapper;
import com.myproject.product.service.ProductService;
import com.myproject.request.*;
import com.myproject.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.*;
import java.util.stream.Collectors;

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
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private CartClient cartClient;
    @Autowired
    private CollectClient collectClient;   //TODO:太臃肿了，考虑把controller分开写

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

    @Override
    public R getProductListById(ProductIdListRequest productIdListRequest) {
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.in("product_id",productIdListRequest.getProductIdList());
        List<Product> productList = productMapper.selectList(productQueryWrapper);
        R ok = R.ok("查询成功", productList);
        log.info("ProductServiceImpl执行结束，结果{getProductListById}",productList);
        return ok;
    }

    @Override
    public List<Product> productsById(ProductIdListRequest productIdListRequest) {
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.in("product_id",productIdListRequest.getProductIdList());
        List<Product> productList = productMapper.selectList(productQueryWrapper);
        return productList;
    }

    @Override
    @Transactional
    public void subNumber(List<OrderToProduct> orderToProducts) {
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));
        Set<Integer> keySet = map.keySet();
        List<Product> productList = productMapper.selectBatchIds(keySet);
        for (Product product : productList) {
            Integer Num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum()-Num);
            product.setProductSales(product.getProductSales()+Num);
        }
        //批量更新
        updateBatchById(productList);
        log.info("ProductServiceImpl执行结束，结果{subNumber修改完毕}");

    }

    @Override
    public Long productCount(Integer categoryId) {
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.eq("category_id",categoryId);
        Long aLong = baseMapper.selectCount(productQueryWrapper);
        return aLong;
    }

    @CacheEvict(value = "list.product",allEntries = true)
    @Override
    public R productSave(ProductSaveRequest productSaveRequest) {
        Product product=new Product();
        BeanUtils.copyProperties(productSaveRequest,product);

        save(product);
        log.info("ProductServiceImpl执行结束，结果{productSave保存}");

        String pictures = productSaveRequest.getPictures();
        if (!StringUtils.isEmpty(pictures)) {
            String[] pictureAdd = pictures.split("\\+");
            List<Picture> pictureList=new ArrayList<>();
            for (String s : pictureAdd) {
                Picture picture=new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(s);
                pictureMapper.insert(picture);   //插入商品图片
            }
        }

        searchClient.saveProduct(product);
        return R.ok("商品数据添加成功");
    }


    @CacheEvict(value = "list.product",allEntries = true)
    @Override
    public R productUpdate(Product product) {
        updateById(product);
        searchClient.saveProduct(product);
        return R.ok("商品更新成功");
    }


    @Caching(evict = {
            @CacheEvict(value = "list.product",allEntries = true),
            @CacheEvict(value = "product",key = "#productId")
    })
    @Override
    public R productRemove(Integer productId) {
        Long cartCount= cartClient.productCount(productId);
        Long orderCount= orderClient.productCount(productId);
        if (cartCount>0) {
            log.info("ProductServiceImpl执行结束，结果{productRemove cartfail}");
            return R.fail("购物车存在引用,删除失败");
        } else if (orderCount>0) {
            log.info("ProductServiceImpl执行结束，结果{productRemove orderfail}");
            return R.fail("订单存在引用,删除失败");
        }
        productMapper.deleteById(productId);
        QueryWrapper<Picture> pictureQueryWrapper=new QueryWrapper<>();
        pictureQueryWrapper.eq("product_id",productId);
        pictureMapper.delete(pictureQueryWrapper);
        collectClient.removeByProductId(productId);
        searchClient.removeProduct(productId);
        return R.ok("商品删除成功");
    }
}
