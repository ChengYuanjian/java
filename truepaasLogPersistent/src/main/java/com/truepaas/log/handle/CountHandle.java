package com.truepaas.log.handle;

import com.google.gson.Gson;
import com.truepaas.log.api.IcountHandle;
import com.truepaas.log.mapper.CountMapper;
import com.truepaas.log.util.ExcelMethod;
import com.truepaas.log.util.UtilMethod;
import com.truepaas.log.vo.CountLog;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
@RestController
@RequestMapping("/log")
public class CountHandle  implements IcountHandle {

    @Autowired
    private CountMapper countMapper;

    @Autowired
    private ExcelMethod excelMethod;

    @ResponseBody
    @RequestMapping("/getAllCounts")
    public List<CountLog> getAllCounts(){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryAllCountLog();
        return list;
    }

    @RequestMapping("/getAllCounts/export")
    public void getAllCounts(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<CountLog> list=countMapper.queryAllCountLog();
        excelMethod.exportCountExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getCountsByIndicator/{indicator}")
    public List<CountLog> getCountsByIndicator(@PathVariable String indicator){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryCountLogByIndicator(indicator);
        return list;
    }

    @RequestMapping("/getCountsByIndicator/{indicator}/export")
    public void getCountsByIndicator(HttpServletResponse response,@PathVariable String indicator){
        List<CountLog> list =countMapper.queryCountLogByIndicator(indicator);
        excelMethod.exportCountExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAllCountsByTime/{startTime}/{endTime}")
    public List<CountLog> getAllCountsByTime(@PathVariable String startTime,@PathVariable String endTime){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryAllCountLogByTime(startTime,endTime);
        return list;
    }

    @RequestMapping("/getAllCountsByTime/{startTime}/{endTime}/export")
    public void getAllCountsByTime(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime){
        List<CountLog> list =countMapper.queryAllCountLogByTime(startTime,endTime);
        excelMethod.exportCountExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getCountsByIndicatorAndTime/{indicator}/{startTime}/{endTime}")
    public List<CountLog> getCountsByIndicatorAndTime(@PathVariable String indicator,@PathVariable String startTime,@PathVariable String endTime){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryCountLogByIndicatorAndTime(indicator,startTime,endTime);
        return list;
    }

    @RequestMapping("/getCountsByIndicatorAndTime/{indicator}/{startTime}/{endTime}/export")
    public void getCountsByIndicatorAndTime(HttpServletResponse response,@PathVariable String indicator,@PathVariable String startTime,@PathVariable String endTime){
        List<CountLog> list =countMapper.queryCountLogByIndicatorAndTime(indicator,startTime,endTime);
        excelMethod.exportCountExcel(list,response);
    }

    //num limit
    @ResponseBody
    @RequestMapping("/getAllCounts/{startNum}/{endNum}")
    public List<CountLog> getAllCounts(@PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryAllCountLogByNum(UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getAllCounts/{startNum}/{endNum}/export")
    public void getAllCounts(HttpServletResponse response,@PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =countMapper.queryAllCountLogByNum(UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportCountExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getCountsByIndicator/{indicator}/{startNum}/{endNum}")
    public List<CountLog> getCountsByIndicator(@PathVariable String indicator,@PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryCountLogByIndicatorAndNum(indicator,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getCountsByIndicator/{indicator}/{startNum}/{endNum}/export")
    public void getCountsByIndicator(HttpServletResponse response,@PathVariable String indicator,@PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =countMapper.queryCountLogByIndicatorAndNum(indicator,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportCountExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAllCountsByTime/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<CountLog> getAllCountsByTime(@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryAllCountLogByTimeAndNum(startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getAllCountsByTime/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getAllCountsByTime(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =countMapper.queryAllCountLogByTimeAndNum(startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportCountExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getCountsByIndicatorAndTime/{indicator}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<CountLog> getCountsByIndicatorAndTime(@PathVariable String indicator,
                                                      @PathVariable String startTime,@PathVariable String endTime,
                                                      @PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =new LinkedList<CountLog>();
        list=countMapper.queryCountLogByIndicatorAndTimeAndNum(indicator,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getCountsByIndicatorAndTime/{indicator}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getCountsByIndicatorAndTime(HttpServletResponse response,@PathVariable String indicator,
                                                      @PathVariable String startTime,@PathVariable String endTime,
                                                      @PathVariable int startNum,@PathVariable int endNum){
        List<CountLog> list =countMapper.queryCountLogByIndicatorAndTimeAndNum(indicator,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportCountExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getTotalAccessTimes")
    public String getTotalAccessTimes(){
        String str=countMapper.getTotalAccessTimes();
        return str;
    }

    @ResponseBody
    @RequestMapping("/getMonthAccessTimes")
    public String getMonthAccessTimes(){
        List<CountLog> list= countMapper.getMonthAccessTimes();
        Gson gson=new Gson();
        String str=gson.toJson(list);
        return str;
    }

    @ResponseBody
    @RequestMapping("/getTotalAccessIps")
    public String getTotalAccessIps(){
        String str=countMapper.getTotalAccessIps();
        return str;
    }

    @ResponseBody
    @RequestMapping("/getAvgAccessDuration")
    public String getAvgAccessDuration(){
        String str=countMapper.getAvgAccessDuration();
        return str;
    }

    @ResponseBody
    @RequestMapping("/getAvgCpu")
    public String getAvgCpu(){
        String str=countMapper.getAvgCpu();
        return str;
    }

    @ResponseBody
    @RequestMapping("/getAvgMem")
    public String getAvgMem(){
        String str=countMapper.getAvgMem();
        return str;
    }

    @ResponseBody
    @RequestMapping("/getAvgDisk")
    public String getAvgDisk(){
        String str=countMapper.getAvgDisk();
        return str;
    }
}
