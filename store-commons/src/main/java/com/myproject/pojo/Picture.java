package com.myproject.pojo;/**
 * ClassName: Picture
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
 * @description: 图片实体类
 *
 * @author: ljr
 *
 * @create: 2023-09-08 22:01
 **/
@TableName("product_picture")
@Data
public class Picture implements Serializable {
    public static final Long serialVersionUid= 1L;
    @TableId(type= IdType.AUTO)
    private Integer id;

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_picture")
    private String productPicture;

    private String intro;

}
