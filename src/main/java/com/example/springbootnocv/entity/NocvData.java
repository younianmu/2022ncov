package com.example.springbootnocv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@TableName("nocv_data")
@Data
public class NocvData {
    // 设置id自增
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer value;
    private Date updateTime;
}
