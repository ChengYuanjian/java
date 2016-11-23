package com.truepaas.log.bolt.baseBolt;

import com.google.gson.Gson;
import com.truepaas.log.util.LogDurable;
import com.truepaas.log.vo.HttpLog;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mas on 2016/10/26.
 */
public class HttpDetailBolt extends BaseBasicBolt {

    public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        String word = input.getString(0);
        if (word.contains("doppler") && word.contains("HttpStartStop")) {
            String regex = "\\{(.*)+\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                String info = matcher.group(0);
                if (!word.contains("\"status_code\":200")) {
                    collector.emit("error", new Values(info));
                } else {
                    //发射给后面的bolt处理
//                    collector.emit("appCount", new Values(info));
//                    collector.emit("urlCount", new Values(info));
//                    collector.emit("urlTime", new Values(info));
//                    collector.emit("IPCount", new Values(info));
                    collector.emit("countInfo",new Values(info));
                    Gson gson = new Gson();
                    HttpLog log = gson.fromJson(info, HttpLog.class);
                    LogDurable.adjustTime(log.getTime());
                    if(null!=log.getForwarded()&&null!=log.getForwarded()[0]){
                        log.setIp(log.getForwarded()[0]);
                    }
                    LogDurable.postHttpLog("addHttpLog", log);
                }
            }
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//        declarer.declareStream("appCount", new Fields("message"));
//        declarer.declareStream("urlCount", new Fields("message"));
//        declarer.declareStream("urlTime", new Fields("message"));
//        declarer.declareStream("IPCount", new Fields("message"));
        declarer.declareStream("countInfo", new Fields("message"));
        declarer.declareStream("error", new Fields("error"));
    }
}