package com.myproject.request;/**
 * ClassName: AddressRemoveRequest
 * Package: com.myproject.request
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @program: my-store
 *
 * @description: 地址删除用类
 *
 * @author: ljr
 *
 * @create: 2023-07-20 14:03
 **/
@Data
@NoArgsConstructor
public class AddressRemoveRequest {
    @NotNull
    private Integer id;
}
