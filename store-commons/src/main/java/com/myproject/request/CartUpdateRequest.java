package com.myproject.request;/**
 * ClassName: CartUpdateRequest
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
 * @create: 2023-09-12 10:13
 **/
@Data
public class CartUpdateRequest extends CartSaveRequest{
    @NotNull
    private Integer num;
}
