package com.example.springbootnocv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootnocv.mapper.IndexMapper;
import com.example.springbootnocv.entity.LineTrend;
import com.example.springbootnocv.entity.NocvData;
import com.example.springbootnocv.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, NocvData> implements IndexService {
    @Autowired
    private IndexMapper indexMapper;

    @Override
    public List<LineTrend> findSevenData() {
        List<LineTrend> list = indexMapper.findSevenData();
        return list;
    }

    @Override
    public List<NocvData> listOrderByIdLimit34() {
        return indexMapper.listOrderByIdLimit34();
    }
}
