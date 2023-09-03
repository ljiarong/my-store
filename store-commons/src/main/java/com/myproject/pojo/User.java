package com.myproject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
* 
* @TableName user
*/
@TableName("user")
@Data
public class User implements Serializable {
    public static final Long serialVersionUID=1L;

    /**
    * 
    */
    @TableId(type = IdType.AUTO)
    @JsonProperty("user_id")   //json-key
    private Integer userId;
    /**
    * 
    */
    @Length(min = 6)
    @NotBlank
    private String userName;
    /**
    * 
    */
    @NotBlank
//    @JsonIgnore   //不接收和生成json
    @JsonInclude(JsonInclude.Include.NON_NULL)   //为空不返回json
    private String password;
    /**
    * 
    */
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPhonenumber;


}
