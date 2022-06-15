package com.example.springbootnocv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootnocv.entity.HealthClock;
import com.example.springbootnocv.mapper.HealthClockMapper;
import com.example.springbootnocv.service.HealthClockService;
import org.springframework.stereotype.Service;

@Service
public class HealthClockServiceImpl extends ServiceImpl<HealthClockMapper, HealthClock> implements HealthClockService {

}
