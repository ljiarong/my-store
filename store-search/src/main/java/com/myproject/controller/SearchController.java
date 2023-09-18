package com.myproject.controller;/**
 * ClassName: SearchController
 * Package: com.myproject.controller
 */

import com.myproject.pojo.Product;
import com.myproject.request.ProductSearchRequest;
import com.myproject.service.ProductSearchService;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @program: my-store
 *
 * @description: 搜索控制器
 *
 * @author: ljr
 *
 * @create: 2023-09-10 21:53
 **/
@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private ProductSearchService productSearchService;
    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchRequest productSearchRequest){
        return productSearchService.searchProduct(productSearchRequest);
    }

    @PostMapping("save")
    public R saveProduct(@RequestBody Product product) throws IOException {
        return productSearchService.saveProduct(product);
    }

    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {
        return productSearchService.removeProduct(productId);
    }
}
