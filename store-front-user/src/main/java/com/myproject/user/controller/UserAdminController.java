package com.myproject.user.controller;/**
 * ClassName: UserAdminController
 * Package: com.myproject.user.controller
 */

import com.myproject.pojo.User;
import com.myproject.request.PageRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.user.service.UserService;
import com.myproject.utils.R;
import lombok.val;
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
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-14 23:37
 **/
@RestController
@RequestMapping("user")
public class UserAdminController {
    @Autowired
    UserService userService;
    @PostMapping("admin/list")
    public R userList(@RequestBody PageRequest pageRequest){
        return userService.userList(pageRequest);
    }

    @PostMapping("admin/remove")
    public R userRemove(@RequestBody UserIdRequest userIdRequest){
        return userService.userRemove(userIdRequest.getUserId());
    }

    @PostMapping("admin/update")
    public R userUpdate(@RequestBody @Validated User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("修改失败,参数不符合要求");
        }
        return userService.userUpdate(user);
    }

    @PostMapping("admin/save")
    public R userSave(@RequestBody @Validated User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("用户添加失败,用户名最少为6位,且参数不能为空");
        }
        return userService.userSave(user);
    }
}
