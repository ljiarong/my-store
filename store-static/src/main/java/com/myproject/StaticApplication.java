package com.myproject;/**
 * ClassName: StaticApplication
 * Package: com.myproject
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: my-store
 *
 * @description: 静态资源启动类
 *
 * @author: ljr
 *
 * @create: 2023-07-20 14:29
 **/
@SpringBootApplication
public class StaticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticApplication.class,args);
    }
}
