package com.truepaas.log.util;

import com.truepaas.log.vo.AppLog;
import com.truepaas.log.vo.CountLog;
import com.truepaas.log.vo.HttpLog;
import com.truepaas.log.vo.OperLog;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mas on 2016/9/30.
 */
public  class LogDurable {
    private static String ip="192.168.1.56";
    private static String port="8080";

    public  static String getUrl(String method){
        return "http://"+ip+":"+port +"/"+method;
    }

    //提交到服务器上时间相同(-0)，本地调试需要-8
    public static void adjustTime(Date date){
         date.setHours(date.getHours()-0);
    }

    public static String convertDateToStr(Date date){
        // 输出格式: 2015-1-27 00:00:00 大写H为24小时制
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String convertDateToStr1(Date date){
        // 输出格式: 2015-1-27 00:00:00 大写H为24小时制
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String checkStr(String str){
        if (str==null) {
            return "";
        }
        return str;
    }

    public static String convertTimeStampToStr(Long timestamp){
        DateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =new Date(timestamp/1000000);
        //提交到服务器上需要+8，本地则+0
        date.setHours(date.getHours()+8);
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String postAppLog(String method, AppLog log){
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(getUrl(method));
        postMethod.addParameter("cf_app_id",checkStr(log.getCf_app_id()));
        postMethod.addParameter("cf_app_name",checkStr(log.getCf_app_name()));
        postMethod.addParameter("cf_org_id",checkStr(log.getCf_org_id()));
        postMethod.addParameter("cf_org_name",checkStr(log.getCf_org_name()));
        postMethod.addParameter("cf_space_id",checkStr(log.getCf_space_id()));
        postMethod.addParameter("cf_space_name",checkStr(log.getCf_space_name()));
        postMethod.addParameter("cpu_percentage",checkStr(log.getCpu_percentage()));
        double d1=Double.valueOf(log.getDisk_bytes());
        double d2=Double.valueOf(log.getMemory_bytes());
        postMethod.addParameter("disk_bytes",String.valueOf(d1/1024/1024));
        postMethod.addParameter("memory_bytes",String.valueOf(d2/1024/1024));
        postMethod.addParameter("time",convertDateToStr(log.getTime()));
        //设置字符编码，否则存在mysql中的中文为乱码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//        postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
//        postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");

        try {
            httpClient.executeMethod(postMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = postMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return responseMsg;
    }

    public static String postHttpLog(String method, HttpLog log){
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(getUrl(method));
        postMethod.addParameter("cf_app_id",checkStr(log.getCf_app_id()));
        postMethod.addParameter("cf_app_name",checkStr(log.getCf_app_name()));
        postMethod.addParameter("cf_org_id",checkStr(log.getCf_org_id()));
        postMethod.addParameter("cf_org_name",checkStr(log.getCf_org_name()));
        postMethod.addParameter("cf_space_id",checkStr(log.getCf_space_id()));
        postMethod.addParameter("cf_space_name",checkStr(log.getCf_space_name()));
        postMethod.addParameter("uri",checkStr(log.getUri()));
        postMethod.addParameter("instance_id",checkStr(log.getInstance_id()));
        postMethod.addParameter("instance_index",checkStr(log.getInstance_index()));
        postMethod.addParameter("ip",checkStr(log.getIp()));
        postMethod.addParameter("user_agent",log.getUser_agent());
        postMethod.addParameter("start_timestamp",convertTimeStampToStr(Long.parseLong(log.getStart_timestamp())));
        postMethod.addParameter("stop_timestamp",convertTimeStampToStr(Long.parseLong(log.getStop_timestamp())));
        postMethod.addParameter("time",convertDateToStr(log.getTime()));
        //设置字符编码，否则存在mysql中的中文为乱码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//        postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
//        postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");

        try {
            httpClient.executeMethod(postMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = postMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return responseMsg;
    }

    public static String postOperLog(String method, OperLog log){
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(getUrl(method));
        postMethod.addParameter("cf_app_id",checkStr(log.getCf_app_id()));
        postMethod.addParameter("cf_app_name",checkStr(log.getCf_app_name()));
        postMethod.addParameter("cf_org_id",checkStr(log.getCf_org_id()));
        postMethod.addParameter("cf_org_name",checkStr(log.getCf_org_name()));
        postMethod.addParameter("cf_space_id",checkStr(log.getCf_space_id()));
        postMethod.addParameter("cf_space_name",checkStr(log.getCf_space_name()));
        postMethod.addParameter("msg",checkStr(log.getMsg()));
        postMethod.addParameter("time",convertDateToStr(log.getTime()));
        //设置字符编码，否则存在mysql中的中文为乱码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//        postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
//        postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");

        try {
            httpClient.executeMethod(postMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = postMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return responseMsg;
    }

    public static String postErrorLog(String method, HttpLog log){
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(getUrl(method));
        postMethod.addParameter("cf_app_id",checkStr(log.getCf_app_id()));
        postMethod.addParameter("cf_app_name",checkStr(log.getCf_app_name()));
        postMethod.addParameter("cf_org_id",checkStr(log.getCf_org_id()));
        postMethod.addParameter("cf_org_name",checkStr(log.getCf_org_name()));
        postMethod.addParameter("cf_space_id",checkStr(log.getCf_space_id()));
        postMethod.addParameter("cf_space_name",checkStr(log.getCf_space_name()));
        postMethod.addParameter("uri",checkStr(log.getUri()));
        postMethod.addParameter("status_code",checkStr(log.getStatus_code()));
        postMethod.addParameter("time",convertDateToStr(log.getTime()));
        //设置字符编码，否则存在mysql中的中文为乱码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//        postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
//        postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");

        try {
            httpClient.executeMethod(postMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = postMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return responseMsg;
    }


    public static String getHttp(String method) {
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(getUrl(method));
        try {
            httpClient.executeMethod(getMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = getMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            getMethod.releaseConnection();
        }
        return responseMsg;
    }

    public static String postCountLog(String method, CountLog log){
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(getUrl(method));
        postMethod.addParameter("cf_app_id",checkStr(log.getCf_app_id()));
        postMethod.addParameter("cf_app_name",checkStr(log.getCf_app_name()));
        postMethod.addParameter("cf_org_id",checkStr(log.getCf_org_id()));
        postMethod.addParameter("cf_space_id",checkStr(log.getCf_space_id()));
        postMethod.addParameter("indicator",checkStr(log.getIndicator()));
        postMethod.addParameter("indicator_value",checkStr(log.getIndicator_value().toString()));
        postMethod.addParameter("indicator_desc",checkStr(log.getIndicator_desc()));
        postMethod.addParameter("time",checkStr(log.getTime()));
        //设置字符编码，否则存在mysql中的中文为乱码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//        postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
//        postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");

        try {
            httpClient.executeMethod(postMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = postMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return responseMsg;
    }

    public static String updateCountLog(String method,String str){
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(getUrl(method));
        postMethod.addParameter("content",str);
        //设置字符编码，否则存在mysql中的中文为乱码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//        postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
//        postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");

        try {
            httpClient.executeMethod(postMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = postMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return responseMsg;
    }

}
