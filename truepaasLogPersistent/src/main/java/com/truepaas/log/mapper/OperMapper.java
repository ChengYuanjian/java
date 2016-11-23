package com.truepaas.log.mapper;

import com.truepaas.log.vo.OperLog;

import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
public interface OperMapper {
    List<OperLog> queryAllOperLog();
    List<OperLog> queryOperLogByAppId(String id);
    List<OperLog> queryOperLogByOrgId(String id);
    List<OperLog> queryOperLogBySpaId(String id);
    List<OperLog> queryAllOperLogByTime(String startTime,String endTime);
    List<OperLog> queryOperLogByAppIdAndTime(String id,String startTime,String endTime);
    List<OperLog> queryOperLogByOrgIdAndTime(String id,String startTime,String endTime);
    List<OperLog> queryOperLogBySpaIdAndTime(String id,String startTime,String endTime);
    //num limit
    List<OperLog> queryAllOperLogByNum(int startNum,int endNum);
    List<OperLog> queryOperLogByAppIdAndNum(String id,int startNum,int endNumv);
    List<OperLog> queryOperLogByOrgIdAndNum(String id,int startNum,int endNum);
    List<OperLog> queryOperLogBySpaIdAndNum(String id,int startNum,int endNum);
    List<OperLog> queryAllOperLogByTimeAndNum(String startTime,String endTime,int startNum,int endNum);
    List<OperLog> queryOperLogByAppIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
    List<OperLog> queryOperLogByOrgIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
    List<OperLog> queryOperLogBySpaIdAndTimeAndNum(String id,String startTime,String endTime,int startNum,int endNum);
}
