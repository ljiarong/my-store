package com.myproject.clients;

import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: CollectClient
 * Package: com.myproject.clients
 */
@FeignClient("collect-service")
public interface CollectClient {

    @PostMapping("collect/remove/product")
    R removeByProductId(@RequestBody Integer productId);
}
