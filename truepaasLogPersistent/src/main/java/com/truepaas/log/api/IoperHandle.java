package com.truepaas.log.api;

import com.truepaas.log.vo.OperLog;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
public interface IoperHandle {
    List<OperLog> getAllOpers();
    List<OperLog> getOpersByAppId(@PathVariable String id);
    List<OperLog> getOpersByOrgId(@PathVariable String orgId);
    List<OperLog> getOpersBySpaId(@PathVariable String spaId);
    List<OperLog> getAllOpersByTime(@PathVariable String startTime,@PathVariable String endTime);
    List<OperLog> getOpersByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime);
    List<OperLog> getOpersByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime);
    List<OperLog> getOpersBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime);

    //num limit
    List<OperLog> getAllOpers(@PathVariable int startNum,@PathVariable int endNum);
    List<OperLog> getOpersByAppId(@PathVariable String id,@PathVariable int startNum,@PathVariable int endNum);
    List<OperLog> getOpersByOrgId(@PathVariable String orgId,@PathVariable int startNum,@PathVariable int endNum);
    List<OperLog> getOpersBySpaId(@PathVariable String spaId,@PathVariable int startNum,@PathVariable int endNum);
    List<OperLog> getAllOpersByTime(@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    List<OperLog> getOpersByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    List<OperLog> getOpersByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    List<OperLog> getOpersBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);

}
