package com.truepaas.log.config;

import com.truepaas.log.api.IappHandle;
import com.truepaas.log.api.IcountHandle;
import com.truepaas.log.api.IhttpHandle;
import com.truepaas.log.api.IoperHandle;
import com.truepaas.log.handle.AppHandle;
import com.truepaas.log.handle.CountHandle;
import com.truepaas.log.handle.HttpHandle;
import com.truepaas.log.handle.OperHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mas on 2016/11/7.
 */
@Configuration
public class ControllerConfig {
    @Bean
    public IappHandle getAppHandle(){
        return new AppHandle();
    }

    @Bean
    public IhttpHandle getHttpHandle(){
        return new HttpHandle();
    }

    @Bean
    public IoperHandle getOperHandle(){
        return new OperHandle();
    }

    @Bean
    public IcountHandle getCountHandle(){
        return new CountHandle();
    }
}
