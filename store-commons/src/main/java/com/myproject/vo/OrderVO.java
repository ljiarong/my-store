package com.myproject.vo;/**
 * ClassName: OrderVO
 * Package: com.myproject.vo
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.pojo.Order;
import lombok.Data;

/**
 * @program: my-store
 *
 * @description: 订单vo
 *
 * @author: ljr
 *
 * @create: 2023-09-13 21:30
 **/
@Data
public class OrderVO extends Order {
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;
}
