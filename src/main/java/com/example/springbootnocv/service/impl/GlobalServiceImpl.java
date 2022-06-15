package com.example.springbootnocv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootnocv.entity.NocvGlobalData;
import com.example.springbootnocv.mapper.GlobalDataMapper;
import com.example.springbootnocv.service.GlobalService;
import org.springframework.stereotype.Service;

@Service
public class GlobalServiceImpl extends ServiceImpl<GlobalDataMapper, NocvGlobalData> implements GlobalService {

}
