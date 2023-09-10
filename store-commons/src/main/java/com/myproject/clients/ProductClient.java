package com.myproject.clients;

import com.myproject.pojo.Product;
import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ClassName: ProductClient
 * Package: com.myproject.clients
 */
@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("product/list")
    public List<Product> getAllProduct();
}
