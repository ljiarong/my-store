package com.myproject.request;/**
 * ClassName: UserIdRequest
 * Package: com.myproject.request
 */

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-11 21:44
 **/
@Data
public class UserIdRequest {
    @NotNull
    private Integer userId;
}
