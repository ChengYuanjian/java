package com.truepaas.log.api;

import com.truepaas.log.vo.AppLog;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
public interface IappHandle {
    List<AppLog> getAllApps();
    List<AppLog> getAllAppsByTime(@PathVariable String starTime,@PathVariable String endTime);
    List<AppLog> getAppsByAppId(@PathVariable String id);
    List<AppLog> getAppsByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime);
    List<AppLog> getAppsByOrgId(@PathVariable String orgId);
    List<AppLog> getAppsByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime);
    List<AppLog> getAppsBySpaId(@PathVariable String spaId);
    List<AppLog> getAppsBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime);
    //num limit
    List<AppLog> getAllApps(@PathVariable int startNum,@PathVariable int endNum);
    List<AppLog> getAllAppsByTime(@PathVariable String starTime,@PathVariable String endTime,
                                  @PathVariable int startNum,@PathVariable int endNum);
    List<AppLog> getAppsByAppId(@PathVariable String id,@PathVariable int startNum,@PathVariable int endNum);
    List<AppLog> getAppsByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime,
                                       @PathVariable int startNum,@PathVariable int endNum);
    List<AppLog> getAppsByOrgId(@PathVariable String orgId,@PathVariable int startNum,@PathVariable int endNum);
    List<AppLog> getAppsByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime,
                                       @PathVariable int startNum,@PathVariable int endNum);
    List<AppLog> getAppsBySpaId(@PathVariable String spaId,@PathVariable int startNum,@PathVariable int endNum);
    List<AppLog> getAppsBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime,
                                       @PathVariable int startNum,@PathVariable int endNum);
}
