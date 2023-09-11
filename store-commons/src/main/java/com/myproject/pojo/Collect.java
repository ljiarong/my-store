package com.myproject.pojo;/**
 * ClassName: Collect
 * Package: com.myproject.pojo
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: my-store
 *
 * @description: 收藏实体类
 *
 * @author: ljr
 *
 * @create: 2023-09-11 20:47
 **/
@Data
@TableName("collect")
public class Collect implements Serializable {
    public static final Long serialVersionUid= 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonProperty("user_id")
    @TableField("user_id")
    @NotNull
    private Integer userId;

    @JsonProperty("product_id")
    @TableField("product_id")
    @NotNull
    private Integer productId;

    @TableField("collect_time")
    private Long collectTime;
}
