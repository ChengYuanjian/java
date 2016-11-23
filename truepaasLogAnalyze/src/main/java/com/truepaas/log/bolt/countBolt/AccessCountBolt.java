package com.truepaas.log.bolt.countBolt;

import com.google.gson.Gson;
import com.truepaas.log.util.LogDurable;
import com.truepaas.log.vo.CountLog;
import com.truepaas.log.vo.HttpLog;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mas on 2016/11/9.
 */
public class AccessCountBolt extends BaseBasicBolt {

    private Date baseDate;
    private boolean isSetDate=false;
    private Map<String,CountLog> map=new HashMap<String, CountLog>();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");

    private boolean compareDate(Date d1,Date d2){
        if((df.format(d1)).equals(df.format(d2))){
            return false;
        }else {
            baseDate=d2;
            return true;
        }
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String info=input.getStringByField("message");
        System.out.println(info);
        Gson gson=new Gson();
        HttpLog log =gson.fromJson(info, HttpLog.class);
        LogDurable.adjustTime(log.getTime());
        if(null!=log.getForwarded()&&null!=log.getForwarded()[0]){
            log.setIp(log.getForwarded()[0]);
        }
        String ip=log.getIp();
        if(map.containsKey(log.getIp())){
            Integer num =(Integer) map.get(ip).getIndicator_value();
            map.get(ip).setIndicator_value(num+1);
        }else{
            //定义countLog
            CountLog cLog=new CountLog();
            cLog.setCf_app_id(log.getCf_app_id());
            cLog.setCf_app_name(log.getCf_app_name());
            cLog.setCf_org_id(log.getCf_org_id());
            cLog.setCf_space_id(log.getCf_space_id());
            cLog.setIndicator("access count");
            cLog.setIndicator_value(1);
            cLog.setIndicator_desc(log.getIp());
            cLog.setTime(LogDurable.convertDateToStr1(log.getTime()));
            map.put(cLog.getIndicator_desc(),cLog);
            //写入一笔基础数据入库
            LogDurable.postCountLog("addCountLog",cLog);
        }
        if(!isSetDate){
            baseDate=log.getTime();
            isSetDate=true;
        }
        //一个小时范围内的数据统计信息入库
        if(compareDate(baseDate,log.getTime())){
            String str=gson.toJson(map);
            LogDurable.updateCountLog("updateCountLog",str);
            map.clear();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {}

}