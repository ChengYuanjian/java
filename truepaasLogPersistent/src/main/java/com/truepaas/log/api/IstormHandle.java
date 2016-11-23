package com.truepaas.log.api;

import com.truepaas.log.vo.*;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by mas on 2016/11/3.
 */
public interface IstormHandle {
    String addtest();
    String addAppLog(@ModelAttribute AppLog log);
    String addHttpLog(@ModelAttribute HttpLog log);
    String addOperLog(@ModelAttribute OperLog log);
    String addErrorLog(@ModelAttribute HttpLog log);
    String addCountLog(@ModelAttribute CountLog log);
    String updateCountLog(@ModelAttribute postContent content);
}
