package com.truepaas.log;

import com.truepaas.log.config.BaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mas on 2016/9/8.
 */

@RestController
public class MainClass {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/log")
    String log() {
        return "Welcome to use truepaas log api!";
    }

    public static void main(String[] args) throws Exception {
        BaseConfig baseConfig=new BaseConfig();
        SpringApplication.run( new Object[]{
                baseConfig.getClass(),
                MainClass.class}, args);
    }
}
