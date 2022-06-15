package com.example.springbootnocv.tengxunapi;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootnocv.entity.ChinaTotal;
import com.example.springbootnocv.entity.NocvData;
import com.example.springbootnocv.service.ChinaTotalService;
import com.example.springbootnocv.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ChinaTotalScheduleTask {

    @Autowired
    private ChinaTotalService chinaTotalService;
    @Autowired
    private IndexService indexService;

    /**
     * 每小时执行一次，更新全国数据情况
     * @throws Exception
     */
    @Scheduled(fixedDelay = 10000000) //1000S执行一次
    //@Scheduled(cron="0 0 8,9,10,11,12,13,18,20 * * ?")
    public void updateChinaTotalToDB() throws Exception {
        HttpUtils httpUtils = new HttpUtils();
        String string = httpUtils.getData();
//        System.out.println("原始数据：" + string);
        // 1.所有数据的alibaba格式
        JSONObject jsonObject = JSONObject.parseObject(string);
        Object data = jsonObject.get("data");
//        System.out.println("data:" + data);
        // 2.data
        JSONObject jsonObjectData = JSONObject.parseObject(data.toString());
        Object chinaTotal = jsonObjectData.get("chinaTotal");
        Object lastUpdateTime = jsonObjectData.get("overseaLastUpdateTime");
//        System.out.println("chinaTotal:" + chinaTotal);
        // 3.total 全中国整体疫情数据
        JSONObject jsonObjectTotal = JSONObject.parseObject(chinaTotal.toString());
        Object total = jsonObjectTotal.get("total");
//        System.out.println("total:" + total);
        // 4.全国数据total
        JSONObject totalData = JSONObject.parseObject(total.toString());
        Object confirm = totalData.get("confirm");
        Object input = totalData.get("input");
        Object severe = totalData.get("severe");
        Object heal = totalData.get("heal");
        Object dead = totalData.get("dead");
        Object suspect = totalData.get("suspect");
        // 5.为程序实体赋值
        ChinaTotal dataEntity = new ChinaTotal();
        dataEntity.setConfirm((Integer) confirm);
        dataEntity.setInput((Integer) input);
        dataEntity.setSevere((Integer) severe);
        dataEntity.setHeal((Integer) heal);
        dataEntity.setDead((Integer) dead);
        dataEntity.setSuspect((Integer) suspect);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dataEntity.setUpdateTime(format.parse(String.valueOf(lastUpdateTime)));

        // 6.插入数据库
        chinaTotalService.save(dataEntity);

        /**
         * 从接口中获取各省份数据并实现自动刷新
         */
        // 拿到areaTree
        JSONArray areaTree = jsonObjectData.getJSONArray("areaTree");
        Object[] objects = areaTree.toArray();
        // 遍历所有国家
//        for (int i =0; i<objects.length;i++){
//            JSONObject jsonObject1 = JSONObject.parseObject(objects[i].toString());
//            Object name = jsonObject1.get("name");
//            System.out.println(name);
//        }
        // 拿到中国数据
        JSONObject jsonObject1 = JSONObject.parseObject(objects[2].toString());
        JSONArray children = jsonObject1.getJSONArray("children");
        Object[] objects1 = children.toArray(); // 各省份
        List<NocvData> list = new ArrayList<>();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i =0; i<objects1.length;i++){
            NocvData nocvData = new NocvData();
            JSONObject jsonObject2 = JSONObject.parseObject(objects1[i].toString());
            Object name = jsonObject2.get("name"); // 获取省份名称
            Object time = jsonObject2.get("lastUpdateTime");
            Object total1 = jsonObject2.get("total"); // 获取省份名称
            JSONObject jsonObject3 = JSONObject.parseObject(total1.toString()); // 获取total里的数据
            // 累计确诊确诊数量
            Object confirm1 = jsonObject3.get("confirm");
            // 获取累计治愈数据
            Object heal1 = jsonObject3.get("heal");
            // 获取累计死亡人数
            Object dead1 = jsonObject3.get("dead");

            // 计算现存的确诊人数
            int xiancunConfirm = Integer.parseInt(confirm1.toString()) - Integer.parseInt(heal1.toString()) - Integer.parseInt(dead1.toString());


            // 给每个实体赋值
            nocvData.setValue(xiancunConfirm);
            nocvData.setName(name.toString());
            if (time == null){
                nocvData.setUpdateTime(new Date());
            }else {
                nocvData.setUpdateTime(format1.parse(String.valueOf(time)));
            }

            list.add(nocvData);
        }
        indexService.saveBatch(list);
    }
}
