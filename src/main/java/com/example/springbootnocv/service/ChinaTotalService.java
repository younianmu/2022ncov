package com.example.springbootnocv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootnocv.entity.ChinaTotal;
import org.apache.ibatis.annotations.Select;

public interface ChinaTotalService extends IService<ChinaTotal> {
    Integer maxID();
}
