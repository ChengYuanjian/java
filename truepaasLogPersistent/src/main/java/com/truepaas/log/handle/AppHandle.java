package com.truepaas.log.handle;

import com.truepaas.log.api.IappHandle;
import com.truepaas.log.mapper.AppMapper;
import com.truepaas.log.util.ExcelMethod;
import com.truepaas.log.util.UtilMethod;
import com.truepaas.log.vo.AppLog;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mas on 2016/11/4.
 */
@RestController
@RequestMapping("/log")
public class AppHandle implements IappHandle {
    @Autowired
    private AppMapper appMapper;

    @Autowired
    private ExcelMethod excelMethod;

    @ResponseBody
    @RequestMapping("/test")
    public List<AppLog> getTest(HttpServletRequest hsr) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAllAppLog();
        return list;
    }

    @ResponseBody
    @RequestMapping("/getAllApps")
    public List<AppLog> getAllApps() {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAllAppLog();
        return list;
    }

    @RequestMapping("/getAllApps/export")
    public void getAppLog(HttpServletResponse response) throws Exception{
        List<AppLog> list=appMapper.queryAllAppLog();
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAllAppsByTime/{startTime}/{endTime}")
    public List<AppLog> getAllAppsByTime(@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAllAppLogByTime(startTime,endTime);
        return list;
    }

    @RequestMapping("/getAllAppsByTime/{startTime}/{endTime}/export")
    public void getAllAppsByTime(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=appMapper.queryAllAppLogByTime(startTime,endTime);
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByAppId/{appId}")
    public List<AppLog> getAppsByAppId(@PathVariable String appId) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByAppId(appId);
        return list;
    }

    @RequestMapping("/getAppsByAppId/{appId}/export")
    public void getAppsByAppId(HttpServletResponse response,@PathVariable String appId) {
        List<AppLog> list=appMapper.queryAppLogByAppId(appId);
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByAppIdAndTime/{appId}/{startTime}/{endTime}")
    public List<AppLog> getAppsByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByAppIdAndTime(appId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getAppsByAppIdAndTime/{appId}/{startTime}/{endTime}/export")
    public void getAppsByAppIdAndTime(HttpServletResponse response,@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=appMapper.queryAppLogByAppIdAndTime(appId,startTime,endTime);
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByAppIdAndTime/{appId}/{startTime}/{endTime}/{number}")
    public List<AppLog> getAppsByAppIdAndTime(@PathVariable String appId,
                                              @PathVariable String startTime,
                                              @PathVariable String endTime,
                                              @PathVariable Integer number) {

        List<AppLog> list=new LinkedList<AppLog>();
        List<AppLog> newList=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByAppIdAndTime1(appId,startTime,endTime);
        Integer num=list.size()/number;
        Integer plus=list.size()%number;
        if(num.equals(0)){
            return list;
        }else{
            Double dou=Double.valueOf(list.get(0).getCpu_percentage());
            for(int i=1;i<plus+num;i++){
                dou=(dou+Double.valueOf(list.get(i).getCpu_percentage()))/2;
            }
            //first log
            AppLog log1=list.get(plus+num-1);
            log1.setCpu_percentage(String.valueOf(dou));
            newList.add(log1);
            //other logs init
            AppLog otherLog = new AppLog();
            //init double
            Double otherDou=Double.valueOf(0);
            for(int j=plus+num,k=plus+num;j<list.size();j++){
                if(k==j){
                    //创建新的log 每一次new一个新的对象
                    otherLog=new AppLog();
                    otherLog=list.get(j);
                    otherDou=Double.valueOf(otherLog.getCpu_percentage());
                }
                otherDou=(otherDou+Double.valueOf(list.get(j).getCpu_percentage()))/2;
                otherLog.setCpu_percentage(otherDou.toString());
                if(k+num==j+1){
                    newList.add(otherLog);
                    k=j+1;
                }
            }
            Collections.reverse(newList);
            return newList;
        }
    }

    @ResponseBody
    @RequestMapping("/getAppsByOrgId/{orgId}")
    public List<AppLog> getAppsByOrgId(@PathVariable String orgId) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByOrgId(orgId);
        return list;
    }

    @RequestMapping("/getAppsByOrgId/{orgId}/export")
    public void getAppsByOrgId(HttpServletResponse response,@PathVariable String orgId) {
        List<AppLog> list=appMapper.queryAppLogByOrgId(orgId);
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByOrgIdAndTime/{orgId}/{startTime}/{endTime}")
    public List<AppLog> getAppsByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByOrgIdAndTime(orgId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getAppsByOrgIdAndTime/{orgId}/{startTime}/{endTime}/export")
    public void getAppsByOrgIdAndTime(HttpServletResponse response,@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=appMapper.queryAppLogByOrgIdAndTime(orgId,startTime,endTime);
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsBySpaId/{spaId}")
    public List<AppLog> getAppsBySpaId(@PathVariable String spaId) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogBySpaId(spaId);
        return list;
    }

    @RequestMapping("/getAppsBySpaId/{spaId}/export")
    public void getAppsBySpaId(HttpServletResponse response,@PathVariable String spaId) {
        List<AppLog> list=appMapper.queryAppLogBySpaId(spaId);
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsBySpaIdAndTime/{spaId}/{startTime}/{endTime}")
    public List<AppLog> getAppsBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogBySpaIdAndTime(spaId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getAppsBySpaIdAndTime/{spaId}/{startTime}/{endTime}/export")
    public void getAppsBySpaIdAndTime(HttpServletResponse response,@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime) {
        List<AppLog> list=appMapper.queryAppLogBySpaIdAndTime(spaId,startTime,endTime);
        excelMethod.exportAppExcel(list,response);
    }


    //num limit
    @ResponseBody
    @RequestMapping("/getAllApps/{startNum}/{endNum}")
    public List<AppLog> getAllApps(@PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAllAppLogByNum(UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAllApps/{startNum}/{endNum}/export")
    public void getAllApps(HttpServletResponse response,@PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAllAppLogByNum(UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAllAppsByTime/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<AppLog> getAllAppsByTime(@PathVariable String startTime,@PathVariable String endTime,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAllAppLogByTimeAndNum(startTime,endTime,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAllAppsByTime/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getAllAppsByTime(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAllAppLogByTimeAndNum(startTime,endTime,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByAppId/{appId}/{startNum}/{endNum}")
    public List<AppLog> getAppsByAppId(@PathVariable String appId,
                                       @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByAppIdAndNum(appId,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAppsByAppId/{appId}/{startNum}/{endNum}/export")
    public void getAppsByAppId(HttpServletResponse response,@PathVariable String appId,
                                       @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAppLogByAppIdAndNum(appId,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByAppIdAndTime/{appId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<AppLog> getAppsByAppIdAndTime(@PathVariable String appId,
                                              @PathVariable String startTime, @PathVariable String endTime,
                                              @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByAppIdAndTimeAndNum(appId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAppsByAppIdAndTime/{appId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getAppsByAppIdAndTime(HttpServletResponse response,
                                      @PathVariable String appId,
                                              @PathVariable String startTime, @PathVariable String endTime,
                                              @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAppLogByAppIdAndTimeAndNum(appId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByOrgId/{orgId}/{startNum}/{endNum}")
    public List<AppLog> getAppsByOrgId(@PathVariable String orgId,
                                       @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByOrgIdAndNum(orgId,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAppsByOrgId/{orgId}/{startNum}/{endNum}/export")
    public void getAppsByOrgId(HttpServletResponse response,@PathVariable String orgId,
                                       @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAppLogByOrgIdAndNum(orgId,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsByOrgIdAndTime/{orgId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<AppLog> getAppsByOrgIdAndTime(@PathVariable String orgId,
                                              @PathVariable String startTime,@PathVariable String endTime,
                                              @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogByOrgIdAndTimeAndNum(orgId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAppsByOrgIdAndTime/{orgId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getAppsByOrgIdAndTime(HttpServletResponse response,@PathVariable String orgId,
                                              @PathVariable String startTime,@PathVariable String endTime,
                                              @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAppLogByOrgIdAndTimeAndNum(orgId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsBySpaId/{spaId}/{startNum}/{endNum}")
    public List<AppLog> getAppsBySpaId(@PathVariable String spaId,
                                       @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogBySpaIdAndNum(spaId,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAppsBySpaId/{spaId}/{startNum}/{endNum}/export")
    public void getAppsBySpaId(HttpServletResponse response, @PathVariable String spaId,
                                       @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAppLogBySpaIdAndNum(spaId,UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAppsBySpaIdAndTime/{spaId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<AppLog> getAppsBySpaIdAndTime(@PathVariable String spaId,
                                              @PathVariable String startTime,@PathVariable String endTime,
                                              @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=new LinkedList<AppLog>();
        list=appMapper.queryAppLogBySpaIdAndTimeAndNum(spaId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        return list;
    }

    @RequestMapping("/getAppsBySpaIdAndTime/{spaId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getAppsBySpaIdAndTime(HttpServletResponse response,@PathVariable String spaId,
                                              @PathVariable String startTime,@PathVariable String endTime,
                                              @PathVariable int startNum,@PathVariable int endNum) {
        List<AppLog> list=appMapper.queryAppLogBySpaIdAndTimeAndNum(spaId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum,endNum));
        excelMethod.exportAppExcel(list,response);
    }
}
