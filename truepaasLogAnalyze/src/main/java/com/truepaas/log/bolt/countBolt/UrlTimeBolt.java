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
 * Created by mas on 2016/10/21.
 */
public class UrlTimeBolt extends BaseBasicBolt {
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
        Gson gson=new Gson();
        HttpLog log =gson.fromJson(info, HttpLog.class);
        LogDurable.adjustTime(log.getTime());
        String httpUrl=log.getUri();
        double t1= Double.valueOf(log.getStart_timestamp());
        double t2 =Double.valueOf(log.getStop_timestamp());
        //单位ms
        double t=(t2-t1)/1000000;
        if(map.containsKey(httpUrl)){
            Double dou=(Double)map.get(httpUrl).getIndicator_value();
            map.get(httpUrl).setIndicator_value((dou+t)/2);
        }else{
            //定义countLog
            CountLog cLog=new CountLog();
            cLog.setCf_app_id(log.getCf_app_id());
            cLog.setCf_app_name(log.getCf_app_name());
            cLog.setCf_org_id(log.getCf_org_id());
            cLog.setCf_space_id(log.getCf_space_id());
            cLog.setIndicator("url time");
            cLog.setIndicator_value(t);
            cLog.setIndicator_desc(httpUrl);
            cLog.setTime(LogDurable.convertDateToStr1(log.getTime()));
            map.put(httpUrl,cLog);
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