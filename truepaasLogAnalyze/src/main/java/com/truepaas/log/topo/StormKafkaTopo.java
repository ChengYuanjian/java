package com.truepaas.log.topo;

/**
 * Created by mas on 2016/10/26.
 */

import com.truepaas.log.bolt.baseBolt.AppDetailBolt;
import com.truepaas.log.bolt.baseBolt.HttpDetailBolt;
import com.truepaas.log.bolt.baseBolt.OperDetailBolt;
import com.truepaas.log.bolt.countBolt.*;
import com.truepaas.log.spout.MessageScheme;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StormKafkaTopo {
    public static void main(String[] args) throws Exception {
        Properties pps = new Properties();
        pps.load(Object.class.getResourceAsStream("/config.properties"));
        String[] str=pps.getProperty("zookeeper").split(",");
        List<String> servers = new ArrayList<String>();
        for (int i=0;i<str.length;i++){
            servers.add(str[i]);
        }
        //配置Zookeeper地址
        BrokerHosts brokerHosts = new ZkHosts(pps.getProperty("zkHosts"));
        //配置Kafka订阅的Topic，以及zookeeper中数据节点目录和id,处理http日志
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "httpLog", "/httpLog" , "httpLog");
        spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());
        //设置之后可以确保spout不重复获取已经获取的数据
        //*********************
        spoutConfig.zkPort = Integer.valueOf(pps.getProperty("zkPort"));
        spoutConfig.zkServers=servers;
        //*********************
        //设置失败重传次数限制
        spoutConfig.retryLimit=Integer.valueOf(pps.getProperty("retryLimit"));

        //配置Kafka订阅的Topic，以及zookeeper中数据节点目录和id,处理app日志
        SpoutConfig spoutConfig1 = new SpoutConfig(brokerHosts, "appLog", "/appLog" , "appLog");
        spoutConfig1.scheme = new SchemeAsMultiScheme(new MessageScheme());
        //设置之后可以确保spout不重复获取已经获取的数据
        //*********************
        spoutConfig1.zkPort = Integer.valueOf(pps.getProperty("zkPort"));
        spoutConfig1.zkServers=servers;
        //设置失败重传次数限制
        spoutConfig1.retryLimit=Integer.valueOf(pps.getProperty("retryLimit"));

        //配置Kafka订阅的Topic，以及zookeeper中数据节点目录和id,处理operation日志
        SpoutConfig spoutConfig2 = new SpoutConfig(brokerHosts, "operLog", "/operLog" , "operLog");
        spoutConfig2.scheme = new SchemeAsMultiScheme(new MessageScheme());
        //设置之后可以确保spout不重复获取已经获取的数据
        //*********************
        spoutConfig2.zkPort = Integer.valueOf(pps.getProperty("zkPort"));
        spoutConfig2.zkServers=servers;
        //设置失败重传次数限制
        spoutConfig2.retryLimit=Integer.valueOf(pps.getProperty("retryLimit"));

        //构造处理http日志的topology
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout", new KafkaSpout(spoutConfig));
        builder.setBolt("baseBolt", new HttpDetailBolt(),2).setNumTasks(4).shuffleGrouping("kafkaSpout");
//        builder.setBolt("appCountBolt", new AppCountBolt()).globalGrouping("baseBolt","appCount");
//        builder.setBolt("urlCountBolt", new UrlCountBolt()).globalGrouping("baseBolt","urlCount");
//        builder.setBolt("urlTimeBolt", new UrlTimeBolt()).globalGrouping("baseBolt","urlTime");
//        builder.setBolt("IPCountBolt", new IPCountBolt()).globalGrouping("baseBolt","IPCount");
        builder.setBolt("appCountBolt", new AppCountBolt()).globalGrouping("baseBolt","countInfo");
        builder.setBolt("urlCountBolt", new UrlCountBolt()).globalGrouping("baseBolt","countInfo");
        builder.setBolt("urlTimeBolt", new UrlTimeBolt()).globalGrouping("baseBolt","countInfo");
        builder.setBolt("IPCountBolt", new IPCountBolt()).globalGrouping("baseBolt","countInfo");
        builder.setBolt("accessCountBolt", new AccessCountBolt()).globalGrouping("baseBolt","countInfo");
        builder.setBolt("urlErrorBolt", new UrlErrorBolt()).shuffleGrouping("baseBolt","error");

        //构造处理app日志的topology
        TopologyBuilder builder1 = new TopologyBuilder();
        builder1.setSpout("kafkaSpout", new KafkaSpout(spoutConfig1));
        builder1.setBolt("baseBolt", new AppDetailBolt()).shuffleGrouping("kafkaSpout");
        builder1.setBolt("cpuCountBolt", new CpuCountBolt()).globalGrouping("baseBolt","loadInfo");
        builder1.setBolt("memCountBolt", new MemCountBolt()).globalGrouping("baseBolt","loadInfo");
        builder1.setBolt("diskCountBolt", new DiskCountBolt()).globalGrouping("baseBolt","loadInfo");

        //构造处理operation日志的topology
        TopologyBuilder builder2 = new TopologyBuilder();
        builder2.setSpout("kafkaSpout", new KafkaSpout(spoutConfig2));
        builder2.setBolt("baseBolt", new OperDetailBolt()).shuffleGrouping("kafkaSpout");

        if (args != null && args.length > 0) {
            Config httpConf =new Config();
            Config appConf =new Config();
            Config operConf =new Config();
            httpConf.setNumWorkers(Integer.valueOf(pps.getProperty("httpNumWorkers")));
            appConf.setNumWorkers(Integer.valueOf(pps.getProperty("appNumWorkers")));
            operConf.setNumWorkers(Integer.valueOf(pps.getProperty("operNumWorkers")));
            StormSubmitter.submitTopology(args[0], httpConf, builder.createTopology());
            StormSubmitter.submitTopology(args[1], appConf, builder1.createTopology());
            StormSubmitter.submitTopology(args[2], operConf, builder2.createTopology());
        } else {
            LocalCluster cluster = new LocalCluster();
            Config conf =new Config();
            cluster.submitTopology("httpTopo", conf, builder.createTopology());
            cluster.submitTopology("appTopo", conf, builder1.createTopology());
            cluster.submitTopology("operTopo", conf, builder2.createTopology());
            Utils.sleep(10000000);
            cluster.killTopology("httpTopo");
            cluster.killTopology("appTopo");
            cluster.killTopology("operTopo");
            cluster.shutdown();
        }
    }
}
