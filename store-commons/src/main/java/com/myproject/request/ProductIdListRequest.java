package com.myproject.request;/**
 * ClassName: ProductIdListRequest
 * Package: com.myproject.request
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-11 21:33
 **/
@Data
@NoArgsConstructor
public class ProductIdListRequest {
    @NotEmpty
    private List<Integer> productIdList;
}
