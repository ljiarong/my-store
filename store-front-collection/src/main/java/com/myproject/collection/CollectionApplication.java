package com.myproject.collection;/**
 * ClassName: CollectionApplication
 * Package: com.myproject.collection
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
 * @create: 2023-09-11 17:44
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.myproject.collection.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CollectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectionApplication.class,args);
    }
}
