package com.myproject.request;/**
 * ClassName: UserCheckRequest
 * Package: com.myproject.request
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: my-store
 *
 * @description: 接收前端请求进行校验
 *
 * @author: ljr
 *
 * @create: 2023-07-19 10:02
 **/
@Data
public class UserCheckRequest {

    @NotBlank
    private String userName;   //JsonKey
}
