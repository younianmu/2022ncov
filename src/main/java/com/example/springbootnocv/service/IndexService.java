package com.example.springbootnocv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootnocv.entity.LineTrend;
import com.example.springbootnocv.entity.NocvData;

import java.util.List;

public interface IndexService extends IService<NocvData> {
    List<LineTrend> findSevenData();

    List<NocvData> listOrderByIdLimit34();
}
