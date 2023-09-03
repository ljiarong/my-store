package com.myproject.request;/**
 * ClassName: CategoryNameRequest
 * Package: com.myproject.request
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: my-store
 *
 * @description: 类别校验类
 *
 * @author: ljr
 *
 * @create: 2023-07-21 15:03
 **/
@Data
public class CategoryNameRequest {
    @NotBlank
    private String categoryName;
}
