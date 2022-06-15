package com.example.springbootnocv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootnocv.entity.ChinaTotal;
import com.example.springbootnocv.mapper.ChinaTotalMapper;
import com.example.springbootnocv.service.ChinaTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChinaTotalMapperImpl extends ServiceImpl<ChinaTotalMapper, ChinaTotal> implements ChinaTotalService {
    @Autowired
    private ChinaTotalMapper chinaTotalMapper;

    @Override
    public Integer maxID() {
        return chinaTotalMapper.maxID();
    }
}
