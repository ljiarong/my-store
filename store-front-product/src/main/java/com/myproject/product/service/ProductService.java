package com.myproject.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myproject.clients.ProductClient;
import com.myproject.message.OrderToProduct;
import com.myproject.pojo.Product;
import com.myproject.request.*;
import com.myproject.utils.R;

import java.util.List;

/**
 * ClassName: ProductService
 * Package: com.myproject.product.service
 */
public interface ProductService extends IService<Product> {

    R promo(String categoryName);

    
    /**
    * @Author: ljr
    * @Description: 多类别热门商品查询
    * @DateTime: 2023/9/4
    * @Params: 
    * @Return 
    */
    R hots(CategoryListRequest categoryListRequest);

    R clist();

    /**
    * @Author: ljr
    * @Description: 为空时查询所有商品
    * @DateTime: 2023/9/8
    * @Params: 
    * @Return 
    */
    R getProductByCategoryId(CategoryIdList categoryIdList);

    R detailById(Integer productID);

    R pictureById(Integer productID);

    /**
    * @Author: ljr
    * @Description: 搜索服务获取全部商品数据
    * @DateTime: 2023/9/9
    * @Params:
    * @Return
    */
    List<Product> getAllProduct();

    R searchProduct(ProductSearchRequest productSearchRequest);

    R getProductListById(ProductIdListRequest productIdListRequest);

    List<Product> productsById(ProductIdListRequest productIdListRequest);

    void subNumber(List<OrderToProduct> orderToProducts);

    Long productCount(Integer categoryId);
}
