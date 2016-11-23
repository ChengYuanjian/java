package com.truepaas.log.handle;

import com.truepaas.log.api.IhttpHandle;
import com.truepaas.log.mapper.HttpMapper;
import com.truepaas.log.util.ExcelMethod;
import com.truepaas.log.util.UtilMethod;
import com.truepaas.log.vo.HttpLog;
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
 * Created by mas on 2016/11/3.
 */
@RestController
@RequestMapping("/log")
public class HttpHandle implements IhttpHandle {

    @Autowired
    private HttpMapper httpMapper;

    @Autowired
    private ExcelMethod excelMethod;

    @ResponseBody
    @RequestMapping("/getAllHttps")
    public List<HttpLog> getAllHttps() {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryAllHttpLog();
        return list;
    }


    @RequestMapping("/getAllHttps/export")
    public void getAllHttps(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<HttpLog> list=httpMapper.queryAllHttpLog();
        excelMethod.exportHttpExcel(list,response);
    }


    @ResponseBody
    @RequestMapping("/getAllHttpsByTime/{startTime}/{endTime}")
    public List<HttpLog> getAllHttps(@PathVariable String startTime,@PathVariable String endTime)  {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryAllHttpLogByTime(startTime,endTime);
        return list;
    }

    @RequestMapping("/getAllHttpsByTime/{startTime}/{endTime}/export")
    public void getAllHttps(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime)  {
        List<HttpLog> list=httpMapper.queryAllHttpLogByTime(startTime,endTime);
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByAppId/{appId}")
    public List<HttpLog> getHttpsByAppId(@PathVariable String appId) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByAppId(appId);
        return list;
    }

    @RequestMapping("/getHttpsByAppId/{appId}/export")
    public void getHttpsByAppId(HttpServletResponse response,@PathVariable String appId) {
        List<HttpLog> list=httpMapper.queryHttpLogByAppId(appId);
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByAppIdAndTime/{appId}/{startTime}/{endTime}")
    public List<HttpLog> getHttpsByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByAppIdAndTime(appId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getHttpsByAppIdAndTime/{appId}/{startTime}/{endTime}/export")
    public void getHttpsByAppIdAndTime(HttpServletResponse response,@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime) {
        List<HttpLog> list=httpMapper.queryHttpLogByAppIdAndTime(appId,startTime,endTime);
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByOrgId/{orgId}")
    public List<HttpLog> getHttpsByOrgId(@PathVariable String orgId) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByOrgId(orgId);
        return list;
    }

    @RequestMapping("/getHttpsByOrgId/{orgId}/export")
    public void getHttpsByOrgId(HttpServletResponse respose,@PathVariable String orgId) {
        List<HttpLog> list=httpMapper.queryHttpLogByOrgId(orgId);
        excelMethod.exportHttpExcel(list,respose);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByOrgIdAndTime/{orgId}/{startTime}/{endTime}")
    public List<HttpLog> getHttpsByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByOrgIdAndTime(orgId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getHttpsByOrgIdAndTime/{orgId}/{startTime}/{endTime}/export")
    public void getHttpsByOrgIdAndTime(HttpServletResponse response,@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime) {
        List<HttpLog> list=httpMapper.queryHttpLogByOrgIdAndTime(orgId,startTime,endTime);
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsBySpaId/{spaId}")
    public List<HttpLog> getHttpsBySpaId(@PathVariable String spaId) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogBySpaId(spaId);
        return list;
    }

    @RequestMapping("/getHttpsBySpaId/{spaId}/export")
    public void getHttpsBySpaId(HttpServletResponse response,@PathVariable String spaId) {
        List<HttpLog> list=httpMapper.queryHttpLogBySpaId(spaId);
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsBySpaIdAndTime/{spaId}/{startTime}/{endTime}")
    public List<HttpLog> getHttpsBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogBySpaIdAndTime(spaId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getHttpsBySpaIdAndTime/{spaId}/{startTime}/{endTime}/export")
    public void getHttpsBySpaIdAndTime(HttpServletResponse response,@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime) {
        List<HttpLog> list=httpMapper.queryHttpLogBySpaIdAndTime(spaId,startTime,endTime);
        excelMethod.exportHttpExcel(list,response);
    }

    //num limit

    @ResponseBody
    @RequestMapping("/getAllHttps/{startNum}/{endNum}")
    public List<HttpLog> getAllHttps(@PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryAllHttpLogByNum(UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getAllHttps/{startNum}/{endNum}/export")
    public void getAllHttps(HttpServletResponse response,@PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=httpMapper.queryAllHttpLogByNum(UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAllHttpsByTime/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<HttpLog> getAllHttps(@PathVariable String startTime,@PathVariable String endTime,
                                     @PathVariable int startNum,@PathVariable int endNum)  {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryAllHttpLogByTimeAndNum(startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getAllHttpsByTime/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getAllHttps(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime,
                                     @PathVariable int startNum,@PathVariable int endNum)  {
        List<HttpLog> list=httpMapper.queryAllHttpLogByTimeAndNum(startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByAppId/{appId}/{startNum}/{endNum}")
    public List<HttpLog> getHttpsByAppId(@PathVariable String appId,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByAppIdAndNum(appId,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getHttpsByAppId/{appId}/{startNum}/{endNum}/export")
    public void getHttpsByAppId(HttpServletResponse response,@PathVariable String appId,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=httpMapper.queryHttpLogByAppIdAndNum(appId,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByAppIdAndTime/{appId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<HttpLog> getHttpsByAppIdAndTime(@PathVariable String appId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByAppIdAndTimeAndNum(appId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getHttpsByAppIdAndTime/{appId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getHttpsByAppIdAndTime(HttpServletResponse response,@PathVariable String appId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=httpMapper.queryHttpLogByAppIdAndTimeAndNum(appId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByOrgId/{orgId}/{startNum}/{endNum}")
    public List<HttpLog> getHttpsByOrgId(@PathVariable String orgId,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByOrgIdAndNum(orgId,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getHttpsByOrgId/{orgId}/{startNum}/{endNum}/export")
    public void getHttpsByOrgId(HttpServletResponse response,@PathVariable String orgId,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=httpMapper.queryHttpLogByOrgIdAndNum(orgId,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsByOrgIdAndTime/{orgId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<HttpLog> getHttpsByOrgIdAndTime(@PathVariable String orgId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogByOrgIdAndTimeAndNum(orgId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getHttpsByOrgIdAndTime/{orgId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getHttpsByOrgIdAndTime(HttpServletResponse response,@PathVariable String orgId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=httpMapper.queryHttpLogByOrgIdAndTimeAndNum(orgId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsBySpaId/{spaId}/{startNum}/{endNum}")
    public List<HttpLog> getHttpsBySpaId(@PathVariable String spaId,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogBySpaIdAndNum(spaId,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getHttpsBySpaId/{spaId}/{startNum}/{endNum}/export")
    public void getHttpsBySpaId(HttpServletResponse response,@PathVariable String spaId,
                                         @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=httpMapper.queryHttpLogBySpaIdAndNum(spaId,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getHttpsBySpaIdAndTime/{spaId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<HttpLog> getHttpsBySpaIdAndTime(@PathVariable String spaId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=new LinkedList<HttpLog>();
        list=httpMapper.queryHttpLogBySpaIdAndTimeAndNum(spaId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getHttpsBySpaIdAndTime/{spaId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getHttpsBySpaIdAndTime(HttpServletResponse response,@PathVariable String spaId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<HttpLog> list=httpMapper.queryHttpLogBySpaIdAndTimeAndNum(spaId,startTime,endTime,
                UtilMethod.getStartNum(startNum,endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportHttpExcel(list,response);
    }
}
