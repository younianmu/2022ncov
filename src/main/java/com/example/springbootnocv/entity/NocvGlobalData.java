package com.example.springbootnocv.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@TableName("nocv_global_data")
public class NocvGlobalData {
    //    private Integer id;
    private String name;
    private Integer value;
}
