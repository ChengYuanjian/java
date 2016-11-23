package com.truepaas.log.mapper;

import com.truepaas.log.vo.*;

import java.util.List;

/**
 * Created by mas on 2016/9/8.
 */
public interface StormMapper {
     void addTestInfo(Test test);
     void addAppLog(AppLog log);
     void addHttpLog(HttpLog log);
     void addOperLog(OperLog log);
     void addErrorLog(HttpLog log);
     void addCountLog(CountLog log);
     void updateCountLog(List<CountLog> list);
}
