package com.myproject.request;/**
 * ClassName: UserIdRequest
 * Package: com.myproject.request
 */

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("user_id")
    private Integer userId;
}
