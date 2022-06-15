package com.example.springbootnocv.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.springbootnocv.entity.NocvData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NocvDataVo extends NocvData {
    private Integer page;
    private Integer limit;
}
