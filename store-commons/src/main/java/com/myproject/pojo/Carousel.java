package com.myproject.pojo;/**
 * ClassName: Carousel
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
 * @description: pojo
 *
 * @author: ljr
 *
 * @create: 2023-07-21 11:08
 **/
@Data
@TableName("carousel")
public class Carousel implements Serializable {
    public static final Long serialVersionUid= 1L;
    @TableId(type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;
    private String imgPath;
    private String describes;
    @JsonProperty("product_id")
    private Integer productId;
    private Integer priority;
}
