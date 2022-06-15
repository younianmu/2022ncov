package com.example.springbootnocv.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootnocv.entity.NocvData;
import com.example.springbootnocv.service.IndexService;
import com.example.springbootnocv.vo.DataView;
import com.example.springbootnocv.vo.NocvDataVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChinaDataAdminController {
    @Autowired
    private IndexService indexService;

    // 跳转到Chinadatamanager.html
    @RequestMapping("/toChinaManager")
    public String toChinaManager(){
        return "admin/Chinadatamanager";
    }

    // 模糊查询
    @GetMapping("/listDataPage")
    @ResponseBody
    public DataView listDataByPage(NocvDataVo nocvDataVo){
        // 创建分页对象 当前页每页限制大小
        IPage<NocvData> page = new Page<>(nocvDataVo.getPage(), nocvDataVo.getLimit());
        // 创建模糊查询
        QueryWrapper<NocvData> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!(nocvDataVo.getName() == null),"name",nocvDataVo.getName());
        // 确诊最多的排在最上面
        queryWrapper.orderByDesc("value");
        // 查询数据库
        indexService.page(page,queryWrapper);
        // 返回数据
        DataView dataView = new DataView(page.getTotal(),page.getRecords());
        return dataView;
    }


    // 删除按钮
    @ResponseBody
    @RequestMapping("/china/deleteById")
    public DataView deleteById(Integer id){
        indexService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除成功");
        return dataView;
    }

    @RequestMapping("/china/addOrUpdateChina")
    @ResponseBody
    public DataView addOrUpdateChina(NocvData nocvData){
        boolean save = indexService.saveOrUpdate(nocvData);
        DataView dataView = new DataView();
        if (save){
            dataView.setCode(200);
            dataView.setMsg("新增成功");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("新增失败");
        return dataView;
    }

    /**
     *excel拖拽上传
     * 1. 前端发送请求，上传文件
     * 2. controller里处理参数
     * 3. POI 解析数据
     */
    @RequestMapping("/excelImportChina")
    @ResponseBody
    public DataView excelImportChina(@RequestParam("file") MultipartFile file){
        DataView dataView = new DataView();
        // 判断文件不能为空
        if (file.isEmpty()) {
            dataView.setMsg("文件为空");
        }

        // POI获取excel解析数据
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);

        // 定义一个集合接收数据
        List<NocvData> list = new ArrayList<>();
        XSSFRow row = null;

        // 解析数据
        for (int i = 0;i < sheet.getPhysicalNumberOfRows();i++){
            // 定义实体
            NocvData nocvData = new NocvData();
            // 获取每一行的数据
            row = sheet.getRow(i);
            // 获取城市名称
            nocvData.setName(row.getCell(0).getStringCellValue());
            // 获取人数
            nocvData.setValue((int) row.getCell(1).getNumericCellValue());
            // 添加到list集合中
            list.add(nocvData);
        }

        // 插入数据库
        indexService.saveBatch(list);
        dataView.setCode(200);
        dataView.setMsg("excel数据插入成功");
        return dataView;
    }

    // 导出excel数据
    @ResponseBody
    @RequestMapping("/excelOutPortChina")
    public void excelOutPortChina(HttpServletResponse response) throws Exception{
        // 1.查询数据库
        List<NocvData> list = indexService.list();
        // 2.建立excel对象，封装数据
        response.setCharacterEncoding("UTF-8");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("中国疫情数据sheet1");
        // 2.1创建表头
        XSSFRow xssfRow = sheet.createRow(0);
        xssfRow.createCell(0).setCellValue("城市名称");
        xssfRow.createCell(1).setCellValue("确诊数量");
        // 3.遍历数据
        for (NocvData data : list){
            XSSFRow datarow = sheet.createRow(sheet.getLastRowNum()+1);
            datarow.createCell(0).setCellValue(data.getName());
            datarow.createCell(1).setCellValue(data.getValue());
        }
        // 4.输出流
        OutputStream os = null;
        // 4.1设置excel名字
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + new String("中国疫情数据表".getBytes(),"iso-8859-1") + ".xls");
        // 4.2输出文件
        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        // 5.关闭输出流
        os.close();
    }
}
