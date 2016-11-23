import com.truepaas.log.util.LogDurable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mas on 2016/10/17.
 */
public class test {
    public static void getCurrDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));
    }

    public static  void  main(String[] args) throws Exception{

        Date d2=new Date();
        LogDurable.adjustTime(d2);

        Object obj=new Object();
        obj=1.2;
        Double dou= (Double) obj;


//        Object obj=new Object();
//        obj=10;
//        Integer num=(Integer) obj;
//        num=11;
//
//
//        Date d1=new Date();
//        System.out.print(d1);
//        d1.setHours(d1.getHours()-8);
//        System.out.print(d1);


        double a=Double.valueOf("3");
        double b=10;
        System.out.println(a/b);

        long l =Long.valueOf("1474854883666374577");
        System.out.println(l);
        System.out.println(l/1000);
        DateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        format.setTimeZone(TimeZone.getDefault());

        Date d =new Date();
        long l1=d.getTime();
        System.out.println(l1);
        String formatDate = format.format(new Date(l/1000000));

        System.out.println(formatDate);
        System.out.println(TimeZone.getDefault());
//        String[] str=TimeZone.getAvailableIDs();
//        for(int i=0;i<str.length;i++){
//            format.setTimeZone(TimeZone.getTimeZone(str[i]));
//            String formatDate = format.format(new Date(l/1000000));
//            if(formatDate.contains("2016-10-08")){
//                System.out.println(formatDate);
//                System.out.println(str[i]);
//            }
//        }

//        System.out.println(TimeZone.getDefault());
//        System.out.println(TimeZone.getTimeZone("UTC"));


//        long l1=10;
//        long l2=3;
//        BigDecimal b1;
//        BigDecimal b2;
//        b1=new BigDecimal(l1);
//        b2=new BigDecimal(l2);
//        double d=b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(d);

//        1476005558830844412
//        System.out.println((new Timestamp(new Date().getTime())).toString().subSequence(0, 19));
//        Long l1=new Long("1476005558830844412");
//        Long l2=l1/1000000;
//        Long l3=l2+10000;
//        Long l4=l2/1000;
//        Long l5=l3/1000;
//        Long t4=(new Date(l4)).getTime();
//        Long t5=(new Date(l5)).getTime();
//        System.out.println((t5-t4));
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(new Date(l4)));

//        System.out.println(new Timestamp(l1).toString());
//        System.out.println(df.format(new Date(l2)));
//        System.out.println(df.format(new Date(l3)));
//        Long t1=(new Date(l2)).getTime();
//        Long t2=(new Date(l3)).getTime();
//        System.out.println((new Date(l2)).getTime());
//        System.out.println((new Date(l3)).getTime());
//        System.out.println((t2-t1)/1000);
//        System.out.println(new Date(l2));
//        System.out.println(new Date(l3));

//        Config conf = new Config();
//        //设置两个进程同时处理该topology
//        conf.setNumWorkers(2);
//        TopologyBuilder topologyBuilder = new TopologyBuilder();
//        //下面的参数2代表初始化两个线程同时处理该spout
//        topologyBuilder.setSpout("blue-spout", new BlueSpout(), 2);
//        // setNumTasks(4)代表该bolt包括4个task（即每个线程处理2个task）
//        topologyBuilder.setBolt("green-bolt", new GreenBolt(), 2).setNumTasks(4).shuffleGrouping("blue-spout");
//        topologyBuilder.setBolt("yellow-bolt", new YellowBolt(), 6).shuffleGrouping("green-bolt");
//        StormSubmitter.submitTopology("mytopology", conf, topologyBuilder.createTopology());

    }

    private static Date baseDate;
    public static boolean compareDate(Date d1,Date d2){
        boolean rslt=true;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");//设置日期格式
        if((df.format(d1)).equals(df.format(d2))){
            rslt= false;
        }else{
            baseDate=d2;
        }
        return rslt;
    }
}
