package com.myproject.product;/**
 * ClassName: ProductApplication
 * Package: com.myproject.product
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.myproject.clients.CategoryClient;
import com.myproject.utils.R;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @program: my-store
 *
 * @description: 启动类
 *
 * @author: ljr
 *
 * @create: 2023-07-21 14:13
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.myproject.product.mapper")
@EnableFeignClients(clients = {CategoryClient.class})
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
    @Bean
    MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }   //mybatis-plus自带的分页插件

}
