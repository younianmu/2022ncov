package com.example.springbootnocv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootnocv.entity.LineTrend;
import com.example.springbootnocv.entity.NocvData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IndexMapper extends BaseMapper<NocvData> {
    /**
     * 1. 实现类 在实现类中写业务逻辑
     * 2. 通过XML实现
     * 3. 注解形式
     * @return
     */
    @Select("select * from line_trend ORDER BY create_time desc LIMIT 7")
    List<LineTrend> findSevenData();

    @Select("select * from nocv_data order by id desc limit 34")
    List<NocvData> listOrderByIdLimit34();
}
