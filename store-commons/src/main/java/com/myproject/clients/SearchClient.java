package com.myproject.clients;

import com.myproject.request.ProductSearchRequest;
import com.myproject.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: SearchClient
 * Package: com.myproject.clients
 */
@FeignClient("search-service")
public interface SearchClient {
    @PostMapping("search/product")
    public R searchProduct(@RequestBody ProductSearchRequest productSearchRequest);
}
