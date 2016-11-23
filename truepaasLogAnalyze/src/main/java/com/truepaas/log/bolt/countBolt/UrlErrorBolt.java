package com.truepaas.log.bolt.countBolt;

import com.google.gson.Gson;
import com.truepaas.log.util.LogDurable;
import com.truepaas.log.vo.HttpLog;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

/**
 * Created by mas on 2016/10/24.
 */
public class UrlErrorBolt  extends BaseBasicBolt {

    public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        String info = input.getString(0);
        Gson gson=new Gson();
        HttpLog log =gson.fromJson(info, HttpLog.class);
        LogDurable.adjustTime(log.getTime());
        LogDurable.postErrorLog("addErrorLog",log);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {}

}
