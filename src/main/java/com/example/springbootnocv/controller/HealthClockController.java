package com.example.springbootnocv.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootnocv.entity.HealthClock;
import com.example.springbootnocv.service.HealthClockService;
import com.example.springbootnocv.vo.DataView;
import com.example.springbootnocv.vo.HealthClockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthClockController {
    @Autowired
    private HealthClockService healthClockService;

    // 跳转到healthclock.html
    @RequestMapping("/toHealthClock")
    public String toHealthClock(){
        return "admin/healthclock";
    }

    // 给打卡页面返回数据
    @RequestMapping("/listHealthClock")
    @ResponseBody
    public DataView listHealthClock(HealthClockVo healthClockVo){
        // 查询所有模糊查询条件
        IPage<HealthClock> page = new Page<>(healthClockVo.getPage(), healthClockVo.getLimit());
        QueryWrapper<HealthClock> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(healthClockVo.getUsername() != null,"username",healthClockVo);
        queryWrapper.eq(healthClockVo.getPhone() != null, "phone",healthClockVo);
        healthClockService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    // 新增打卡信息
    @RequestMapping("/addHealthClock")
    @ResponseBody
    public DataView addHealthClock(HealthClock healthClock){
        boolean b = healthClockService.saveOrUpdate(healthClock);
        DataView dataView = new DataView();
        if (b){
            dataView.setCode(200);
            dataView.setMsg("添加成功");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("添加失败");
        return dataView;
    }

    // 删除打卡信息
    @RequestMapping("/deleteHealthClockById")
    @ResponseBody
    public DataView deleteHealthClockById(Integer id){
        healthClockService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除成功");
        return dataView;
    }
}
