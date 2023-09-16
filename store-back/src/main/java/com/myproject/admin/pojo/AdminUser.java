package com.myproject.admin.pojo;/**
 * ClassName: AdminUser
 * Package: com.myproject.admin.pojo
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: my-store
 *
 * @description:
 *
 * @author: ljr
 *
 * @create: 2023-09-14 22:12
 **/
@Data
@TableName("admin_user")
public class AdminUser implements Serializable {
    public static final Long serialVersionUid= 1L;
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userPhone;
    private Date createTime;
    private Integer userRole;
}
