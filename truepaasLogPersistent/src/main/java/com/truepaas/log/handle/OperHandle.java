package com.truepaas.log.handle;

import com.truepaas.log.api.IoperHandle;
import com.truepaas.log.mapper.OperMapper;
import com.truepaas.log.util.ExcelMethod;
import com.truepaas.log.util.UtilMethod;
import com.truepaas.log.vo.OperLog;
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
public class OperHandle implements IoperHandle {

    @Autowired
    private OperMapper operMapper;

    @Autowired
    private ExcelMethod excelMethod;

    @ResponseBody
    @RequestMapping("/getAllOpers")
    public List<OperLog> getAllOpers() {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryAllOperLog();
        return list;
    }

    @RequestMapping("/getAllOpers/export")
    public void getAllOpers(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<OperLog> list=operMapper.queryAllOperLog();
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByAppId/{appId}")
    public List<OperLog> getOpersByAppId(@PathVariable String appId) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByAppId(appId);
        return list;
    }

    @RequestMapping("/getOpersByAppId/{appId}/export")
    public void getOpersByAppId(HttpServletResponse response,@PathVariable String appId) {
        List<OperLog> list=operMapper.queryOperLogByAppId(appId);
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByOrgId/{orgId}")
    public List<OperLog> getOpersByOrgId(@PathVariable String orgId) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByOrgId(orgId);
        return list;
    }

    @RequestMapping("/getOpersByOrgId/{orgId}/export")
    public void getOpersByOrgId(HttpServletResponse response,@PathVariable String orgId) {
        List<OperLog> list=operMapper.queryOperLogByOrgId(orgId);
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersBySpaId/{spaId}")
    public List<OperLog> getOpersBySpaId(@PathVariable String spaId) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogBySpaId(spaId);
        return list;
    }

    @RequestMapping("/getOpersBySpaId/{spaId}/export")
    public void getOpersBySpaId(HttpServletResponse response,@PathVariable String spaId) {
        List<OperLog> list=operMapper.queryOperLogBySpaId(spaId);
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAllOpersByTime/{startTime}/{endTime}")
    public List<OperLog> getAllOpersByTime(@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryAllOperLogByTime(startTime,endTime);
        return list;
    }

    @RequestMapping("/getAllOpersByTime/{startTime}/{endTime}/export")
    public void getAllOpersByTime(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=operMapper.queryAllOperLogByTime(startTime,endTime);
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByAppIdAndTime/{appId}/{startTime}/{endTime}")
    public List<OperLog> getOpersByAppIdAndTime(@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByAppIdAndTime(appId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getOpersByAppIdAndTime/{appId}/{startTime}/{endTime}/export")
    public void getOpersByAppIdAndTime(HttpServletResponse response,@PathVariable String appId,@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=operMapper.queryOperLogByAppIdAndTime(appId,startTime,endTime);
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByOrgIdAndTime/{orgId}/{startTime}/{endTime}")
    public List<OperLog> getOpersByOrgIdAndTime(@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByOrgIdAndTime(orgId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getOpersByOrgIdAndTime/{orgId}/{startTime}/{endTime}/export")
    public void getOpersByOrgIdAndTime(HttpServletResponse response,@PathVariable String orgId,@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=operMapper.queryOperLogByOrgIdAndTime(orgId,startTime,endTime);
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersBySpaIdAndTime/{spaId}/{startTime}/{endTime}")
    public List<OperLog> getOpersBySpaIdAndTime(@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogBySpaIdAndTime(spaId,startTime,endTime);
        return list;
    }

    @RequestMapping("/getOpersBySpaIdAndTime/{spaId}/{startTime}/{endTime}/export")
    public void getOpersBySpaIdAndTime(HttpServletResponse response,@PathVariable String spaId,@PathVariable String startTime,@PathVariable String endTime) {
        List<OperLog> list=operMapper.queryOperLogBySpaIdAndTime(spaId,startTime,endTime);
        excelMethod.exportOperExcel(list,response);
    }

    //num limit
    @ResponseBody
    @RequestMapping("/getAllOpers/{startNum}/{endNum}")
    public List<OperLog> getAllOpers(@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryAllOperLogByNum(UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getAllOpers/{startNum}/{endNum}/export")
    public void getAllOpers(HttpServletResponse response,@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=operMapper.queryAllOperLogByNum(UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByAppId/{appId}/{startNum}/{endNum}")
    public List<OperLog> getOpersByAppId(@PathVariable String appId,@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByAppIdAndNum(appId,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getOpersByAppId/{appId}/{startNum}/{endNum}/export")
    public void getOpersByAppId(HttpServletResponse response,@PathVariable String appId,@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=operMapper.queryOperLogByAppIdAndNum(appId,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByOrgId/{orgId}/{startNum}/{endNum}")
    public List<OperLog> getOpersByOrgId(@PathVariable String orgId,@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByOrgIdAndNum(orgId,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getOpersByOrgId/{orgId}/{startNum}/{endNum}/export")
    public void getOpersByOrgId(HttpServletResponse response,@PathVariable String orgId,@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=operMapper.queryOperLogByOrgIdAndNum(orgId,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersBySpaId/{spaId}/{startNum}/{endNum}")
    public List<OperLog> getOpersBySpaId(@PathVariable String spaId,@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogBySpaIdAndNum(spaId,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getOpersBySpaId/{spaId}/{startNum}/{endNum}/export")
    public void getOpersBySpaId(HttpServletResponse response,@PathVariable String spaId,@PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=operMapper.queryOperLogBySpaIdAndNum(spaId,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getAllOpersByTime/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<OperLog> getAllOpersByTime(@PathVariable String startTime,@PathVariable String endTime,
                                           @PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryAllOperLogByTimeAndNum(startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getAllOpersByTime/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getAllOpersByTime(HttpServletResponse response,@PathVariable String startTime,@PathVariable String endTime,
                                           @PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=operMapper.queryAllOperLogByTimeAndNum(startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByAppIdAndTime/{appId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<OperLog> getOpersByAppIdAndTime(@PathVariable String appId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum){
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByAppIdAndTimeAndNum(appId,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getOpersByAppIdAndTime/{appId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getOpersByAppIdAndTime(HttpServletResponse response,@PathVariable String appId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum){
        List<OperLog> list=operMapper.queryOperLogByAppIdAndTimeAndNum(appId,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersByOrgIdAndTime/{orgId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<OperLog> getOpersByOrgIdAndTime(@PathVariable String orgId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogByOrgIdAndTimeAndNum(orgId,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getOpersByOrgIdAndTime/{orgId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getOpersByOrgIdAndTime(HttpServletResponse response,@PathVariable String orgId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=operMapper.queryOperLogByOrgIdAndTimeAndNum(orgId,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }

    @ResponseBody
    @RequestMapping("/getOpersBySpaIdAndTime/{spaId}/{startTime}/{endTime}/{startNum}/{endNum}")
    public List<OperLog> getOpersBySpaIdAndTime(@PathVariable String spaId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=new LinkedList<OperLog>();
        list=operMapper.queryOperLogBySpaIdAndTimeAndNum(spaId,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        return list;
    }

    @RequestMapping("/getOpersBySpaIdAndTime/{spaId}/{startTime}/{endTime}/{startNum}/{endNum}/export")
    public void getOpersBySpaIdAndTime(HttpServletResponse response,@PathVariable String spaId,
                                                @PathVariable String startTime,@PathVariable String endTime,
                                                @PathVariable int startNum,@PathVariable int endNum) {
        List<OperLog> list=operMapper.queryOperLogBySpaIdAndTimeAndNum(spaId,startTime,endTime,
                UtilMethod.getStartNum(startNum, endNum),
                UtilMethod.getEndNum(startNum, endNum));
        excelMethod.exportOperExcel(list,response);
    }
}
