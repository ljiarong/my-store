package com.myproject.request;/**
 * ClassName: ProductSaveRequest
 * Package: com.myproject.request
 */

import com.myproject.pojo.Product;
import lombok.Data;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-17 22:59
 **/
@Data
public class ProductSaveRequest extends Product {
    private String pictures;//图片地址
}
