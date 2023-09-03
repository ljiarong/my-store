package com.myproject.clients;

import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: CategoryClient
 * Package: com.myproject.clients
 */
@FeignClient("category-service")   //将api转成网络服务，指定服务名称    这个是用spring-cloud来完成的
public interface CategoryClient {
    @GetMapping("/category/promo/{categoryName}")
    R getIdByName(@PathVariable String categoryName);
}
