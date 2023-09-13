package com.myproject.cart.listener;/**
 * ClassName: CartListener
 * Package: com.myproject.cart.listener
 */

import com.myproject.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: my-store
 *
 * @description: 监听mq方法
 *
 * @author: ljr
 *
 * @create: 2023-09-13 17:21
 **/
@Component
public class CartListener {
    @Autowired
    CartService cartService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "clear.queue"),
                    exchange = @Exchange("topic.ex"),
                    key = "clear.cart"
            )
    )
    public void clear(List<Integer> cartIds){
        cartService.clearIds(cartIds);
    }
}
