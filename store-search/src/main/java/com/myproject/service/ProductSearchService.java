package com.myproject.service;

import com.myproject.request.ProductSearchRequest;
import com.myproject.utils.R;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProductSearchService
 * Package: com.myproject.service
 */
public interface ProductSearchService {

    R searchProduct(ProductSearchRequest productSearchRequest);
}
