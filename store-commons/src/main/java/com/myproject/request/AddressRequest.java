package com.myproject.request;/**
 * ClassName: AddressRequest
 * Package: com.myproject.request
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: my-store
 *
 * @description: 地址请求类
 *
 * @author: ljr
 *
 * @create: 2023-09-13 22:59
 **/
@Data
public class AddressRequest {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;
}
