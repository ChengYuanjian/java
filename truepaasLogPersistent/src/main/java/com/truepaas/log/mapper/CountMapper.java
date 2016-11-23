package com.truepaas.log.mapper;

import com.truepaas.log.vo.CountLog;

import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
public interface CountMapper {
    List<CountLog> queryAllCountLog();
    List<CountLog> queryCountLogByIndicator(String indicator);
    List<CountLog> queryAllCountLogByTime(String startTime,String endTime);
    List<CountLog> queryCountLogByIndicatorAndTime(String indicator,String startTime,String endTime);

    //num limit
    List<CountLog> queryAllCountLogByNum(int startNum,int endNum);
    List<CountLog> queryCountLogByIndicatorAndNum(String indicator,int startNum,int endNum);
    List<CountLog> queryAllCountLogByTimeAndNum(String startTime,String endTime,int startNum,int endNum);
    List<CountLog> queryCountLogByIndicatorAndTimeAndNum(String indicator,String startTime,String endTime,int startNum,int endNum);
    String getTotalAccessTimes();
    String getTotalAccessIps();
    String getAvgAccessDuration();
    String getAvgCpu();
    String getAvgMem();
    String getAvgDisk();
    List<CountLog> getMonthAccessTimes();
}
