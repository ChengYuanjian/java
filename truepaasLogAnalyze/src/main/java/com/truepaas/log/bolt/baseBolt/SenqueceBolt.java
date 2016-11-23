package com.truepaas.log.bolt.baseBolt;

import com.google.gson.Gson;
import com.truepaas.log.util.LogDurable;
import com.truepaas.log.vo.AppLog;
import com.truepaas.log.vo.HttpLog;
import com.truepaas.log.vo.OperLog;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SenqueceBolt extends BaseBasicBolt {

    /* (non-Javadoc)
     * @see backtype.storm.topology.IBasicBolt#execute(backtype.storm.tuple.Tuple, backtype.storm.topology.BasicOutputCollector)
     */
    public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        String word = input.getString(0);
        if (word.contains("doppler")&&word.contains("ContainerMetric")){
            String regex = "\\{(.*)\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                String info =matcher.group(0);
                System.out.println(info);
                Gson gson=new Gson();
                AppLog log =gson.fromJson(info, AppLog.class);
                LogDurable.postAppLog("addAppLog",log);
            }
        }else if(word.contains("doppler")&&word.contains("LogMessage")){
            String regex = "\\{(.*)\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                String info =matcher.group(0);
                System.out.println(info);
                Gson gson=new Gson();
                OperLog log =gson.fromJson(info, OperLog.class);
                LogDurable.postOperLog("addOperLog",log);
            }
        }else if(word.contains("doppler")&&word.contains("HttpStartStop")){
            String regex = "\\{(.*)\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                String info =matcher.group(0);
                if (!word.contains("\"status_code\":200")) {
                    collector.emit("error",new Values(info));
                }else{
                    //发射给后面的bolt处理
                    collector.emit("appCount",new Values(info));
                    collector.emit("urlCount",new Values(info));
                    collector.emit("urlTime",new Values(info));
                    collector.emit("IPCount",new Values(info));
                    Gson gson=new Gson();
                    HttpLog log =gson.fromJson(info, HttpLog.class);
                    LogDurable.postHttpLog("addHttpLog",log);
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("appCount",new Fields("message"));
        declarer.declareStream("urlCount",new Fields("message"));
        declarer.declareStream("urlTime",new Fields("message"));
        declarer.declareStream("IPCount",new Fields("message"));
        declarer.declareStream("error",new Fields("error"));
    }
}