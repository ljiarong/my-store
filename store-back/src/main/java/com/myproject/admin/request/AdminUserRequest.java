package com.myproject.admin.request;/**
 * ClassName: AdminUserRequest
 * Package: com.myproject.admin.request
 */

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @program: my-store
 *
 * @description: 接收登录信息的请求
 *
 * @author: ljr
 *
 * @create: 2023-09-14 22:13
 **/
@Data
public class AdminUserRequest {
    @Length(min = 6)
    private String userAccount; //账号
    @Length(min = 6)
    private String userPassword; //密码
    @NotBlank
    private String verCode;  //验证码
}
