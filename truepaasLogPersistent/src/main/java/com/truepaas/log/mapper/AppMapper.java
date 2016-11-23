package com.truepaas.log.mapper;

import com.truepaas.log.vo.AppLog;

import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
public interface AppMapper {
    List<AppLog> queryAllAppLog();
    List<AppLog> queryAllAppLogByTime(String startTime,String endTime);
    List<AppLog> queryAppLogByAppId(String id);
    List<AppLog> queryAppLogByAppIdAndTime(String id,String startTime,String endTime);
    List<AppLog> queryAppLogByAppIdAndTime1(String id,String startTime,String endTime);
    List<AppLog> queryAppLogByOrgId(String id);
    List<AppLog> queryAppLogByOrgIdAndTime(String id,String startTime,String endTime);
    List<AppLog> queryAppLogBySpaId(String id);
    List<AppLog> queryAppLogBySpaIdAndTime(String id,String startTime,String endTime);

    //num limit
    List<AppLog> queryAllAppLogByNum(int startNum,int endNum);
    List<AppLog> queryAllAppLogByTimeAndNum(String startTime,String endTime,int startNum,int endNum );
    List<AppLog> queryAppLogByAppIdAndNum(String id,int startNum,int endNum);
    List<AppLog> queryAppLogByAppIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
    List<AppLog> queryAppLogByOrgIdAndNum(String id,int startNum,int endNum);
    List<AppLog> queryAppLogByOrgIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
    List<AppLog> queryAppLogBySpaIdAndNum(String id,int startNum,int endNum);
    List<AppLog> queryAppLogBySpaIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
}
