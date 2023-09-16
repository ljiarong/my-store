package com.myproject.admin.controller;/**
 * ClassName: CaptchaController
 * Package: com.myproject.admin.controller
 */

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: my-store
 *
 * @description: 验证码控制器
 *
 * @author: ljr
 *
 * @create: 2023-09-14 22:05
 **/
@Controller
public class CaptchaController {
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(request,response);  //自动生成验证码图片写回  并且验证码图片会存储到session,key = captcha
    }
}
