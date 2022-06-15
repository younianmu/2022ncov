package com.example.springbootnocv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    // 设置id自增
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String address;
    private String img;
    private String phone;
    // 加密
    private String salt;
    // 外键
    private Integer roleId;
    private Integer banJiId;
    private Integer xueYuanId;
    private Integer teacherId;
}
