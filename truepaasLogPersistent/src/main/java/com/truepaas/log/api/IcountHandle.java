package com.truepaas.log.api;

import com.truepaas.log.vo.CountLog;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
public interface IcountHandle {
    List<CountLog> getAllCounts();
    List<CountLog> getCountsByIndicator(@PathVariable String indicator);
    List<CountLog> getAllCountsByTime(@PathVariable String startTime,@PathVariable String endTime);
    List<CountLog> getCountsByIndicatorAndTime(@PathVariable String indicator,@PathVariable String startTime,@PathVariable String endTime);
    //num limit
    List<CountLog> getAllCounts(@PathVariable int startNum,@PathVariable int endNum);
    List<CountLog> getCountsByIndicator(@PathVariable String indicator,@PathVariable int startNum,@PathVariable int endNum);
    List<CountLog> getAllCountsByTime(@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    List<CountLog> getCountsByIndicatorAndTime(@PathVariable String indicator,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum);
    String getTotalAccessTimes();
    String getTotalAccessIps();
    String getAvgAccessDuration();
    String getAvgCpu();
    String getAvgMem();
    String getAvgDisk();
    String getMonthAccessTimes();
}
