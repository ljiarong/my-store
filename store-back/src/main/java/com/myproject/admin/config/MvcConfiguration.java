package com.myproject.admin.config;/**
 * ClassName: MvcConfiguration
 * Package: com.myproject.admin.config
 */

import interceptors.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-14 23:23
 **/
@SpringBootConfiguration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * 用户注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截后台管理模块的路径  排除登录和资源路径
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")  //拦截路径 //LoginInterceptor()为之前写的拦截器类
                .excludePathPatterns("/","/index.html","/index","/static/**",   //排除路径
                        "/user/login", "/user/logout",
                        "/api/**", "/css/**", "/images/**",
                        "/js/**", "/lib/**","/captcha"
                );
    }

}
