package com.myproject.admin.controller;/**
 * ClassName: AdminController
 * Package: com.myproject.admin.controller
 */

import com.myproject.admin.pojo.AdminUser;
import com.myproject.admin.request.AdminUserRequest;
import com.myproject.admin.service.AdminService;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-13 23:56
 **/
@RestController
@RequestMapping("user")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PostMapping("login")   //这里前端传的是表单，不是json，所以不能用requestbody注解
    public R userLogin(@Validated AdminUserRequest adminUserRequest, BindingResult bindingResult, HttpSession httpSession){
        if (bindingResult.hasErrors()) {
            return R.fail("参数为空，登录失败");
        }
        String captcha = (String) httpSession.getAttribute("captcha");  //正确的验证码

        if (!adminUserRequest.getVerCode().equalsIgnoreCase(captcha)) {
            return R.fail("验证码错误");
        }
        AdminUser user=adminService.login(adminUserRequest);

        if (user==null){
            return R.fail("登录失败！账号或者密码错误!");
        }
        httpSession.setAttribute("userInfo",user);

        return R.ok("登录成功");
    }

    @GetMapping("logout")
    public R logout(HttpSession httpSession){
        httpSession.invalidate();  //全部清空(包括验证码之类的)
//        httpSession.removeAttribute("userInfo");  //单个key清空
        return R.ok("退出登录成功");
    }
}
