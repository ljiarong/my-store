package com.myproject.clients;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: CartClient
 * Package: com.myproject.clients
 */
@FeignClient("cart-service")
public interface CartClient {
    @PostMapping("cart/count")
    Long productCount(@RequestBody Integer productId);
}
