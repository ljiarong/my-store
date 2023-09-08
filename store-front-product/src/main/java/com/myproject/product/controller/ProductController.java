package com.myproject.product.controller;/**
 * ClassName: ProductController
 * Package: com.myproject.product.controller
 */

import com.myproject.product.service.ProductService;
import com.myproject.request.CategoryIdList;
import com.myproject.request.CategoryListRequest;
import com.myproject.request.CategoryNameRequest;
import com.myproject.request.ProductIdRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description: 控制器
 *
 * @author: ljr
 *
 * @create: 2023-07-21 14:44
 **/
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("promo")
    public R promo(@RequestBody @Validated CategoryNameRequest categoryNameRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("查询类别失败，类别名称不能为空");
        }
        return productService.promo(categoryNameRequest.getCategoryName());
    }

    @PostMapping("hots")
    public R hots(@RequestBody @Validated CategoryListRequest categoryListRequest,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.fail("查询失败，类别列表不能为空");
        }
        return productService.hots(categoryListRequest);
    }

    @PostMapping("category/list")
    public R categoryList(){
        return productService.clist();
    }

    @PostMapping("bycategory")
    public R getProductByCategoryId(@RequestBody @Validated CategoryIdList categoryIdList,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("查询失败,集合不能为null");
        }
        return productService.getProductByCategoryId(categoryIdList);
    }

    @PostMapping("all")
    public R getProductAll(@RequestBody @Validated CategoryIdList categoryIdList,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("查询失败,集合不能为null");
        }
        return productService.getProductByCategoryId(categoryIdList);

    }

    @PostMapping("detail")
    public R getDetailById(@RequestBody @Validated ProductIdRequest productIdRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("id不能为空");
        }
        return productService.detailById(productIdRequest.getProductID());
    }

    @PostMapping("pictures")
    public R getPicturesById(@RequestBody @Validated ProductIdRequest productIdRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("id不能为空");
        }
        return productService.pictureById(productIdRequest.getProductID());
    }
}
