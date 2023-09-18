package com.myproject.product.controller;/**
 * ClassName: ProductController
 * Package: com.myproject.product.controller
 */

import com.myproject.pojo.Product;
import com.myproject.product.service.ProductService;
import com.myproject.request.*;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("collect/list")
    public R getProductListById(@RequestBody @Validated ProductIdListRequest productIdListRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("没有收藏数据");
        }
        return productService.getProductListById(productIdListRequest);
    }

    @PostMapping("admin/count")
    public Long productCount(@RequestBody Integer categoryId){
        return productService.productCount(categoryId);
    }

    @PostMapping("admin/save")
    public R productSave(@RequestBody ProductSaveRequest productSaveRequest){
        return productService.productSave(productSaveRequest);
    }

    @PostMapping("admin/update")
    public R productUpdate(@RequestBody Product product){
        return productService.productUpdate(product);
    }

    @PostMapping("admin/remove")
    public R productRemove(@RequestBody Integer productId){
        return productService.productRemove(productId);
    }

}
