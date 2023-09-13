package com.myproject.message;/**
 * ClassName: OrderToProduct
 * Package: com.myproject.message
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-13 16:32
 **/
@Data
public class OrderToProduct implements Serializable {
    public static final Long serialVersionUid= 1L;  //消息队列选择的是json格式化，所以要实现格式化接口

    private Integer productId;
    private Integer num;
}
