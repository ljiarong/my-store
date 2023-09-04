package com.myproject.request;/**
 * ClassName: CategoryListRequest
 * Package: com.myproject.request
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @program: my-store
 *
 * @description: 类别组
 *
 * @author: ljr
 *
 * @create: 2023-09-04 19:18
 **/
@Data
public class CategoryListRequest {
    @NotEmpty
    private List<String> categoryName;
}
