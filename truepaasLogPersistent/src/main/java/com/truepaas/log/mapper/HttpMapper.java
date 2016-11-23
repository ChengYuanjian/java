package com.truepaas.log.mapper;

import com.truepaas.log.vo.HttpLog;

import java.util.List;

/**
 * Created by mas on 2016/11/3.
 */
public interface HttpMapper {
     List<HttpLog> queryAllHttpLog();
     List<HttpLog> queryHttpLogByAppId(String id);
     List<HttpLog> queryHttpLogByOrgId(String id);
     List<HttpLog> queryHttpLogBySpaId(String id);
     List<HttpLog> queryAllHttpLogByTime(String startTime,String endTime);
     List<HttpLog> queryHttpLogByAppIdAndTime(String id,String startTime,String endTime);
     List<HttpLog> queryHttpLogByOrgIdAndTime(String id,String startTime,String endTime);
     List<HttpLog> queryHttpLogBySpaIdAndTime(String id,String startTime,String endTime);
     //num limit
     List<HttpLog> queryAllHttpLogByNum(int startNum,int endNum);
     List<HttpLog> queryHttpLogByAppIdAndNum(String id,int startNum,int endNum);
     List<HttpLog> queryHttpLogByOrgIdAndNum(String id,int startNum,int endNum);
     List<HttpLog> queryHttpLogBySpaIdAndNum(String id,int startNum,int endNum);
     List<HttpLog> queryAllHttpLogByTimeAndNum(String startTime,String endTime,int startNum,int endNum);
     List<HttpLog> queryHttpLogByAppIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
     List<HttpLog> queryHttpLogByOrgIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
     List<HttpLog> queryHttpLogBySpaIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
}
