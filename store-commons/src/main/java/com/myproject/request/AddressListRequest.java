package com.myproject.request;/**
 * ClassName: AddressRequestParam
 * Package: com.myproject.request
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @program: my-store
 *
 * @description: user_id实体类
 *
 * @author: ljr
 *
 * @create: 2023-07-20 10:06
 **/
@Data
@NoArgsConstructor
public class AddressListRequest {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
