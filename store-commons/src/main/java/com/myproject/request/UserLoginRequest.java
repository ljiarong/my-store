package com.myproject.request;/**
 * ClassName: UserLoginRequest
 * Package: com.myproject.request
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: my-store
 *
 * @description: 登录校验参数
 *
 * @author: ljr
 *
 * @create: 2023-07-19 15:30
 **/
@Data
public class UserLoginRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
