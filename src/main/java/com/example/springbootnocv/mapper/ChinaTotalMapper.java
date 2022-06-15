package com.example.springbootnocv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootnocv.entity.ChinaTotal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChinaTotalMapper extends BaseMapper<ChinaTotal> {
    @Select("select max(id) from china_total")
    Integer maxID();
}
