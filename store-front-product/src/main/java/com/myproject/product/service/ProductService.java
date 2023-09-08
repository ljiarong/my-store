package com.myproject.product.service;

import com.myproject.request.CategoryIdList;
import com.myproject.request.CategoryListRequest;
import com.myproject.request.CategoryNameRequest;
import com.myproject.request.ProductIdRequest;
import com.myproject.utils.R;

/**
 * ClassName: ProductService
 * Package: com.myproject.product.service
 */
public interface ProductService {

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
}
