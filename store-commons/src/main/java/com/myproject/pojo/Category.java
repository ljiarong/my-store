package com.myproject.pojo;/**
 * ClassName: Category
 * Package: com.myproject.pojo
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: my-store
 *
 * @description: 类别
 *
 * @author: ljr
 *
 * @create: 2023-07-21 14:50
 **/
@Data
@TableName("category")
public class Category implements Serializable {
    public static final Long serialVersionUid= 1L;
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    private String categoryName;
}
