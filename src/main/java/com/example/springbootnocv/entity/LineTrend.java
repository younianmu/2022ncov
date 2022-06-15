package com.example.springbootnocv.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@TableName("line_trend")
@Data
public class LineTrend {
    private Integer id;
    private Integer confirm;
    private Integer isolation;
    private Integer cure;
    private Integer dead;
    private Integer similar;
    private Date createTime;
}
