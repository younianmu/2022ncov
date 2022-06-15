package com.example.springbootnocv.controller;

import com.example.springbootnocv.entity.NocvGlobalData;
import com.example.springbootnocv.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GlobalController {
    @Autowired
    private GlobalService globalService;

    /*跳转页面*/
    @RequestMapping("/toGlobal")
    public String toGlobal(){
        return "global";
    }

    @RequestMapping("/queryGlobal")
    @ResponseBody
    public List<NocvGlobalData> queryGlobal(){
        List<NocvGlobalData> list = globalService.list();
        return list;
    }


}
