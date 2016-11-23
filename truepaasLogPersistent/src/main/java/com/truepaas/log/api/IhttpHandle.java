package com.truepaas.log.api;

import com.truepaas.log.vo.HttpLog;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by mas on 2016/11/3.
 */
public interface IhttpHandle {
    List<HttpLog> getAllHttps();
    List<HttpLog> getHttpsByAppId(@PathVariable String id);
    List<HttpLog> getHttpsByOrgId(@PathVariable String orgId);
    List<HttpLog> getHttpsBySpaId(@PathVariable String spaId);
    List<HttpLog> getAllHttps(@PathVariable String startTime,@PathVariable String endTime);
    List<HttpLog> getHttpsByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime);
    List<HttpLog> getHttpsByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime);
    List<HttpLog> getHttpsBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime);

    //num limit
    List<HttpLog> getAllHttps(@PathVariable int startNum,@PathVariable int endNum);
    List<HttpLog> getHttpsByAppId(@PathVariable String id,@PathVariable int startNum,@PathVariable int endNum);
    List<HttpLog> getHttpsByOrgId(@PathVariable String orgId,@PathVariable int startNum,@PathVariable int endNum);
    List<HttpLog> getHttpsBySpaId(@PathVariable String spaId,@PathVariable int startNum,@PathVariable int endNum);
    List<HttpLog> getAllHttps(@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    List<HttpLog> getHttpsByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    List<HttpLog> getHttpsByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    List<HttpLog> getHttpsBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
}
