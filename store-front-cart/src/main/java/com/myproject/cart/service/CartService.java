package com.myproject.cart.service;

import com.myproject.request.CartSaveRequest;
import com.myproject.request.CartUpdateRequest;
import com.myproject.utils.R;

import java.util.List;

/**
 * ClassName: CartService
 * Package: com.myproject.cart.service
 */
public interface CartService {
    R save(CartSaveRequest cartSaveRequest);

    R list(Integer userId);

    R updateCart(CartUpdateRequest cartUpdateRequest);

    R removeCart(CartSaveRequest cartSaveRequest);

    void clearIds(List<Integer> cartIds);

    Long productCount(Integer productId);
}
