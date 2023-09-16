package com.myproject.clients;

import com.myproject.pojo.Product;
import com.myproject.request.ProductIdListRequest;
import com.myproject.request.ProductIdRequest;
import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * ClassName: ProductClient
 * Package: com.myproject.clients
 */
@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("product/list")
    public List<Product> getAllProduct();

    @PostMapping("product/collect/list")
    public R getProductListById(@RequestBody ProductIdListRequest productIdListRequest);

    @PostMapping("product/cart/detail")
    public Product productDetail(@RequestBody ProductIdRequest productIdRequest);

    @PostMapping("product/cart/list")
    public List<Product> productListById(@RequestBody ProductIdListRequest productIdListRequest);
    @PostMapping("product/count")
    public Long productCount(@RequestBody Integer categoryId);
}
