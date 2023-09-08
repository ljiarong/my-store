package com.myproject.request;/**
 * ClassName: ProductIdRequest
 * Package: com.myproject.request
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @program: my-store
 *
 * @description: 商品id接收参数
 *
 * @author: ljr
 *
 * @create: 2023-09-08 18:10
 **/
@Data
@NoArgsConstructor
public class ProductIdRequest {
    @NotNull
    private Integer productID;
}
