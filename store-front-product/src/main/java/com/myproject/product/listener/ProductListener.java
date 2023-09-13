package com.myproject.product.listener;/**
 * ClassName: ProductListener
 * Package: com.myproject.product.listener
 */

import com.myproject.message.OrderToProduct;
import com.myproject.product.service.ProductService;
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
 * @description: mq监听
 *
 * @author: ljr
 *
 * @create: 2023-09-13 17:35
 **/
@Component
public class ProductListener {
    @Autowired
    ProductService productService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sub.queue"),
                    exchange = @Exchange("topic.ex"),
                    key = "sub.number"
            )
    )
    public void subNumber(List<OrderToProduct> orderToProducts){
        productService.subNumber(orderToProducts);
    }
}
