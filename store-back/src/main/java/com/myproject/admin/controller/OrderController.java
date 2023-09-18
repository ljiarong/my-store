package com.myproject.admin.controller;/**
 * ClassName: OrderController
 * Package: com.myproject.admin.controller
 */

import com.myproject.admin.service.AdminService;
import com.myproject.request.PageRequest;
import com.myproject.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-18 19:26
 **/
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private AdminService adminService;
    @GetMapping("list")
    public R orderList(PageRequest pageRequest){
        return adminService.orderList(pageRequest);
    }
}
