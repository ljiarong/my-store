package com.myproject.request;/**
 * ClassName: CategoryIdList
 * Package: com.myproject.request
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @program: my-store
 *
 * @description: 传入商品类别集合
 *
 * @author: ljr
 *
 * @create: 2023-09-08 16:52
 **/
@Data
@NoArgsConstructor
public class CategoryIdList extends PageRequest{
    @NotNull
    private List<Integer> categoryID;
}
