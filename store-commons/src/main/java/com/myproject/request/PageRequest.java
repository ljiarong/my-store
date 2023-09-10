package com.myproject.request;/**
 * ClassName: PageRequest
 * Package: com.myproject.request
 */

import lombok.Data;

/**
 * @program: my-store
 *
 * @description: 分页参数
 *
 * @author: ljr
 *
 * @create: 2023-09-10 21:49
 **/
@Data
public class PageRequest {
    private Integer currentPage=1;
    private Integer pageSize=15;
}
