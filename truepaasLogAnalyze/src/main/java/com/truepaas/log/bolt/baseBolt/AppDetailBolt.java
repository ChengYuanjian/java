package com.truepaas.log.bolt.baseBolt;

import com.google.gson.Gson;
import com.truepaas.log.util.LogDurable;
import com.truepaas.log.vo.AppLog;
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
public class AppDetailBolt extends BaseBasicBolt {

   public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        String word = input.getString(0);
        if (word.contains("doppler")&&word.contains("ContainerMetric")){
            String regex = "\\{(.*)+\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                String info =matcher.group(0);
                collector.emit("loadInfo",new Values(info));
                Gson gson=new Gson();
                AppLog log =gson.fromJson(info, AppLog.class);
                LogDurable.adjustTime(log.getTime());
                LogDurable.postAppLog("addAppLog",log);
            }
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("loadInfo", new Fields("message"));
    }
}