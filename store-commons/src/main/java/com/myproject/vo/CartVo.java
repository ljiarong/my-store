package com.myproject.vo;/**
 * ClassName: CartVo
 * Package: com.myproject.vo
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myproject.pojo.Cart;
import com.myproject.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-11 22:33
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class CartVo {
    private Integer id;
    private Integer productID;
    private String productName;
    private String productImg;
    private Double price;
    private Integer num;
    private Integer maxNum;
    private Boolean check=false;
    public CartVo(Product product, Cart cart){
        this.id= cart.getId();
        this.productID=product.getProductId();
        this.productName=product.getProductName();
        this.productImg=product.getProductPicture();
        this.price=product.getProductPrice();
        this.num=cart.getNum();
        this.maxNum=product.getProductNum();
        this.check=false;
    }
}
