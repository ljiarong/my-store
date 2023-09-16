package com.myproject.admin.controller;/**
 * ClassName: UserController
 * Package: com.myproject.admin.controller
 */

import com.myproject.admin.service.AdminService;
import com.myproject.pojo.User;
import com.myproject.request.PageRequest;
import com.myproject.request.UserIdRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description: 后台用户管理系统
 *
 * @author: ljr
 *
 * @create: 2023-09-14 23:46
 **/
@RestController
public class UserController {
    @Autowired
    private AdminService adminService;
    @GetMapping("user/list")
    public R userList(PageRequest pageRequest){  //普通数据不加requestbody
        return adminService.userList(pageRequest);
    }

    @PostMapping("user/remove")
    public R userRemove(UserIdRequest userIdRequest){
        return adminService.userRemove(userIdRequest);
    }

    @PostMapping("user/update")
    public R userUpdate(User user){
        return adminService.userUpdate(user);
    }

    @PostMapping("user/save")
    public R userSave(User user){
        return adminService.userSave(user);
    }
}
