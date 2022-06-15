package com.example.springbootnocv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootnocv.entity.NocvGlobalData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GlobalDataMapper extends BaseMapper<NocvGlobalData> {
}
