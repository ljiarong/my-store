package com.myproject.cart.controller;/**
 * ClassName: CartController
 * Package: com.myproject.cart.controller
 */

import com.myproject.cart.service.CartService;
import com.myproject.request.CartSaveRequest;
import com.myproject.request.CartUpdateRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-11 22:13
 **/
@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("save")
    public R save(@RequestBody @Validated CartSaveRequest cartSaveRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("参数为空，保存失败");
        }
        return cartService.save(cartSaveRequest);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated UserIdRequest userIdRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("用户id不能为空");
        }
        return cartService.list(userIdRequest.getUserId());
    }

    @PostMapping("update")
    public R updateCart(@RequestBody @Validated CartUpdateRequest cartUpdateRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("更新失败，参数不能为空");
        }
        return cartService.updateCart(cartUpdateRequest);
    }

    @PostMapping("remove")
    public R removeCart(@RequestBody @Validated CartSaveRequest cartSaveRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("修改失败,参数不能为空");
        }
        return cartService.removeCart(cartSaveRequest);
    }

    @PostMapping("count")
    public Long productCount(@RequestBody Integer productId){
        return cartService.productCount(productId);
    }
}
