package com.myproject.product.controller;/**
 * ClassName: ProductCartController
 * Package: com.myproject.product.controller
 */

import com.myproject.pojo.Product;
import com.myproject.product.service.ProductService;
import com.myproject.request.ProductIdListRequest;
import com.myproject.request.ProductIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-11 22:18
 **/
@RestController
@RequestMapping("product")
public class ProductCartController {
    @Autowired
    ProductService productService;
    @RequestMapping("cart/detail")
    public Product cdetail(@RequestBody @Validated ProductIdRequest productIdRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return null;
        }
        return (Product) productService.detailById(productIdRequest.getProductID()).getData();
    }

    @RequestMapping("cart/list")
    public List<Product> productsById(@RequestBody @Validated ProductIdListRequest productIdListRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return null;
        }
        return productService.productsById(productIdListRequest);
    }
}
