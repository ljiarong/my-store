package com.myproject.category;/**
 * ClassName: CategoryApplication
 * Package: com.myproject.category
 */

import com.myproject.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: my-store
 *
 * @description: 启动类
 *
 * @author: ljr
 *
 * @create: 2023-07-21 14:17
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.myproject.category.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class,args);
    }
}
