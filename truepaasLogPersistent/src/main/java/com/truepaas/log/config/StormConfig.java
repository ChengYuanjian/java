package com.truepaas.log.config;

import com.truepaas.log.api.IstormHandle;
import com.truepaas.log.handle.StormHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mas on 2016/11/3.
 */
@Configuration
public class StormConfig {
    @Bean
    public IstormHandle getStormHandle(){
        return new StormHandle();
    }
}
