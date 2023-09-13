package com.myproject.request;/**
 * ClassName: OrederRequest
 * Package: com.myproject.request
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.vo.CartVo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-13 16:09
 **/
@Data
public class OrderRequest {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    @NotEmpty
    private List<CartVo> products;
}
