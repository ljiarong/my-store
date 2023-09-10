package com.myproject.request;/**
 * ClassName: ProductSearchRequest
 * Package: com.myproject.request
 */

import lombok.Data;

/**
 * @program: my-store
 *
 * @description: 搜索传参
 *
 * @author: ljr
 *
 * @create: 2023-09-10 21:47
 **/
@Data
public class ProductSearchRequest extends PageRequest{
    private String search;

}
