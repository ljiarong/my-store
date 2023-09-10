package com.myproject.product.controller;/**
 * ClassName: ProductSearchController
 * Package: com.myproject.product.controller
 */

import com.myproject.clients.ProductClient;
import com.myproject.pojo.Product;
import com.myproject.product.service.ProductService;
import com.myproject.request.ProductSearchRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: my-store
 *
 * @description: 搜索服务的controller
 *
 * @author: ljr
 *
 * @create: 2023-09-09 23:39
 **/
@RestController
@RequestMapping("product")
public class ProductSearchController {
    @Autowired
    private ProductService productService;
    @GetMapping("list")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping("search")
    public R searchProduct(@RequestBody ProductSearchRequest productSearchRequest){
        return productService.searchProduct(productSearchRequest);
    }
}
