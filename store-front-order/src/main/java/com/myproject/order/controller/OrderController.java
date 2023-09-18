package com.myproject.order.controller;/**
 * ClassName: OrderController
 * Package: com.myproject.order.controller
 */

import com.myproject.order.service.OrderService;
import com.myproject.request.OrderRequest;
import com.myproject.request.PageRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;
import com.myproject.vo.OrderVO;
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
 * @create: 2023-09-13 16:13
 **/
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("save")
    public R orderSave(@RequestBody @Validated OrderRequest orderRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.ok("订单保存失败,参数不能为空");
        }
        return orderService.saveOrder(orderRequest);
    }

    @PostMapping("list")
    public R orderList(@RequestBody @Validated UserIdRequest userIdRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("用户Id不能为空");
        }
        return orderService.orderList(userIdRequest.getUserId());

    }

    @PostMapping("count")
    public Long prductCount(@RequestBody Integer productId){
        return orderService.productCount(productId);
    }

    @PostMapping("list/group")
    public R orderListGroup(@RequestBody PageRequest pageRequest){
        return orderService.orderListGroup(pageRequest);
    }
}
