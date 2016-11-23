package com.truepaas.log.handle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.truepaas.log.api.IstormHandle;
import com.truepaas.log.mapper.StormMapper;
import com.truepaas.log.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mas on 2016/11/3.
 */
@RestController
public class StormHandle implements IstormHandle{
    @Autowired
    private StormMapper stormMapper;

    @RequestMapping(value = "/addtest")
    public String addtest() {
        Test test=new Test();
        test.setName("masheng");
        stormMapper.addTestInfo(test);
        return "";
    }

    @RequestMapping(value = "/addAppLog", method = RequestMethod.POST)
    public String addAppLog(@ModelAttribute AppLog log) {
        stormMapper.addAppLog(log);
        return "";
    }

    @RequestMapping(value = "/addHttpLog", method = RequestMethod.POST)
    public String addHttpLog(@ModelAttribute HttpLog log) {
        stormMapper.addHttpLog(log);
        return "";
    }

    @RequestMapping(value = "/addOperLog", method = RequestMethod.POST)
    public String addOperLog(@ModelAttribute OperLog log) {
        stormMapper.addOperLog(log);
        return "";
    }

    @RequestMapping(value = "/addErrorLog", method = RequestMethod.POST)
    public String addErrorLog(@ModelAttribute HttpLog log) {
        stormMapper.addErrorLog(log);
        return "";
    }

    @RequestMapping(value = "/addCountLog", method = RequestMethod.POST)
    public String addCountLog(@ModelAttribute CountLog log) {
        stormMapper.addCountLog(log);
        return "";
    }

    @RequestMapping(value = "/updateCountLog", method = RequestMethod.POST)
    public String updateCountLog(@ModelAttribute postContent content) {
        Gson gson=new Gson();
        Map<String,CountLog> map=gson.fromJson(content.getContent(), new TypeToken<Map<String,CountLog>>(){}.getType());
        List<CountLog> list=new LinkedList<CountLog>();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            CountLog count =map.get(key);
            list.add(count);
        }
        stormMapper.updateCountLog(list);
        return "";
    }

}
