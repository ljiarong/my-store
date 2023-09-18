package com.myproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myproject.pojo.Product;
import com.myproject.request.ProductSearchRequest;
import com.myproject.utils.R;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * ClassName: ProductSearchService
 * Package: com.myproject.service
 */
public interface ProductSearchService {

    R searchProduct(ProductSearchRequest productSearchRequest);

    R saveProduct(Product product) throws IOException;

    R removeProduct(Integer productId) throws IOException;
}
