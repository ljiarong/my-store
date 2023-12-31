package com.myproject.clients;

import com.myproject.request.PageRequest;
import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: OrderClient
 * Package: com.myproject.clients
 */
@FeignClient("order-service")
public interface OrderClient {
    @PostMapping("order/count")
    Long productCount(@RequestBody Integer productId);

    @PostMapping("order/list/group")
    R orderListGroup(@RequestBody PageRequest pageRequest);
}
