package com.myproject.request;/**
 * ClassName: CartSaveRequest
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
 * @create: 2023-09-11 22:40
 **/
@Data
public class CartSaveRequest {
    @NotNull
    @JsonProperty("product_id")
    private Integer productId;

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
