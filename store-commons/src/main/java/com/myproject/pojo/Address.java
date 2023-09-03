package com.myproject.pojo;/**
 * ClassName: Address
 * Package: com.myproject.pojo
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: my-store
 *
 * @description: 地址实体类
 *
 * @author: ljr
 *
 * @create: 2023-07-20 09:55
 **/
@Data
@TableName("address")
public class Address implements Serializable {

    public static final Long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    @NotNull
    private Integer id;  //包装类默认为null,int默认有值
    @NotBlank
    private String linkman;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
//    @TableField("user_id")
    @NotNull
    private String userId;
}
