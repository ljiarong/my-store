package com.myproject.user.controller;/**
 * ClassName: UserController
 * Package: com.myproject.user.controller
 */

import com.myproject.pojo.User;
import com.myproject.request.UserCheckRequest;
import com.myproject.request.UserLoginRequest;
import com.myproject.user.service.UserService;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description: 用户控制器
 *
 * @author: ljr
 *
 * @create: 2023-07-19 10:14
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckRequest userCheckRequest, BindingResult result){
        //hsa-true
        boolean errors = result.hasErrors();
        if (errors){
            return R.fail("账号为空，无法登录");
        }
        return userService.check(userCheckRequest);
    }  //前后端都需要校验

    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult bindingResult){
        boolean hasErrors = bindingResult.hasErrors();
        if(hasErrors){
            return R.fail("填入信息不符合要求");
        }
        return userService.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginRequest userLoginRequest,BindingResult bindingResult){
        boolean hasErrors = bindingResult.hasErrors();
        if(hasErrors){
            return R.fail("用户名或密码不能为空");
        }
        return userService.login(userLoginRequest);
    }
}
