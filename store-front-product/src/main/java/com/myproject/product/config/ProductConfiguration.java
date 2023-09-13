package com.myproject.product.config;/**
 * ClassName: ProductConfiguration
 * Package: com.myproject.product.config
 */

import com.myproject.config.CacheConfiguration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @program: my-store
 *
 * @description: 商品模块配置类
 *
 * @author: ljr
 *
 * @create: 2023-09-11 00:28
 **/
@SpringBootConfiguration
public class ProductConfiguration extends CacheConfiguration {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
