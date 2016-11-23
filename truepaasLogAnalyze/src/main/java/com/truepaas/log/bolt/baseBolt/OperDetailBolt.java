package com.truepaas.log.bolt.baseBolt;

import com.google.gson.Gson;
import com.truepaas.log.util.LogDurable;
import com.truepaas.log.vo.OperLog;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mas on 2016/10/26.
 */
public class OperDetailBolt extends BaseBasicBolt {

    public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        String word = input.getString(0);
        if(word.contains("doppler")&&word.contains("LogMessage")){
            String regex = "\\{(.*)+\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                String info =matcher.group(0);
                Gson gson=new Gson();
                OperLog log =gson.fromJson(info, OperLog.class);
                LogDurable.adjustTime(log.getTime());
                LogDurable.postOperLog("addOperLog",log);
            }
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}