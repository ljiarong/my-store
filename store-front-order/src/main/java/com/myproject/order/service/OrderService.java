package com.myproject.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myproject.pojo.Order;
import com.myproject.request.OrderRequest;
import com.myproject.request.PageRequest;
import com.myproject.utils.R;

/**
 * ClassName: OrderService
 * Package: com.myproject.order.service
 */
public interface OrderService extends IService<Order> {
    R saveOrder(OrderRequest orderRequest);

    R orderList(Integer userId);

    Long productCount(Integer productId);

    R orderListGroup(PageRequest pageRequest);
}
