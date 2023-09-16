package com.myproject.admin;/**
 * ClassName: AdminApplication
 * Package: com.myproject.admin
 */

import com.myproject.clients.CategoryClient;
import com.myproject.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: my-store
 *
 * @description: 启动类
 *
 * @author: ljr
 *
 * @create: 2023-07-20 15:58
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.myproject.admin.mapper")
@EnableCaching
@EnableFeignClients(clients = {CategoryClient.class, UserClient.class})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
