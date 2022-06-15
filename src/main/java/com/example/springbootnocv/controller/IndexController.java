package com.example.springbootnocv.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.ui.Model;
import com.example.springbootnocv.entity.ChinaTotal;
import com.example.springbootnocv.entity.LineTrend;
import com.example.springbootnocv.entity.NocvData;
import com.example.springbootnocv.service.ChinaTotalService;
import com.example.springbootnocv.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private ChinaTotalService chinaTotalService;

    @RequestMapping("/")
    public String index(Model model) throws Exception{
        // 1.找到ID最大的那一条数据
        Integer id = chinaTotalService.maxID();
        // 2.根据ID进行查找数据
        ChinaTotal chinaTotal = chinaTotalService.getById(id);
        model.addAttribute("chinaTotal",chinaTotal);
        return "index";
    }

    // 查询数据库所有数据
    @RequestMapping("/query")
    @ResponseBody
    public List<NocvData> queryData() throws ParseException {
        // 倒序查找34条数据
        List<NocvData> list = indexService.listOrderByIdLimit34();
        return list;
    }

    // 跳转到pie.html
    @RequestMapping("/toPie")
    public String toPie(){
        return "pie";
    }

    // 给饼图返回数据
    @RequestMapping("/queryPie")
    @ResponseBody
    public List<NocvData> queryPieData(){
        List<NocvData> list = indexService.listOrderByIdLimit34();
        return list;
    }

    // 跳转到bar.html
    @RequestMapping("/toBar")
    public String toBar(){
        return "bar";
    }

    // 柱状图返回数据
    @RequestMapping("/queryBar")
    @ResponseBody
    public Map<String,List<Object>> queryBarData(){

        // 所有数据
        List<NocvData> list = indexService.listOrderByIdLimit34();

        // 所有城市数据
        List<String> cityList = new ArrayList<>();
        // 将list中的城市名字放入city中
        for (NocvData data: list) {
            cityList.add(data.getName());
        }

        // 所有城市数据
        List<Integer> dataList = new ArrayList<>();
        // 将list中的人数放入city中
        for (NocvData data: list) {
            dataList.add(data.getValue());
        }

        // 创建Map
        Map map = new HashMap();
        map.put("cityList",cityList);
        map.put("dataList",dataList);
        return map;
    }

    // 跳转到line.html
    @RequestMapping("/toLine")
    public String toLine(){
        return "line";
    }

    // 折线图返回数据
    @RequestMapping("/queryLine")
    @ResponseBody
    public Map<String,List<Object>> queryLineData(){
        // 查询七天所有数据
            List<LineTrend> list7 = indexService.findSevenData();
        // 封装确诊人数
            List<Integer>  comfirmList = new ArrayList<>();
        for (LineTrend data: list7) {
            comfirmList.add(data.getConfirm());
        }
        // 封装隔离人数
            List<Integer>  isolationList = new ArrayList<>();
        for (LineTrend data: list7) {
            isolationList.add(data.getIsolation());
        }
        // 封装治愈人数
            List<Integer>  cureList = new ArrayList<>();
        for (LineTrend data: list7) {
            cureList.add(data.getCure());
        }
        // 封装死亡人数
            List<Integer>  deadList = new ArrayList<>();
        for (LineTrend data: list7) {
            deadList.add(data.getDead());
        }
        // 封装疑似人数
            List<Integer>  similarList = new ArrayList<>();
        for (LineTrend data: list7) {
            similarList.add(data.getSimilar());
        }

        // 返回Map
        Map map = new HashMap();
        map.put("confirmList",comfirmList);
        map.put("isolationList",isolationList);
        map.put("cureList",cureList);
        map.put("deadList",deadList);
        map.put("similarList",similarList);
        return map;
    }

}
