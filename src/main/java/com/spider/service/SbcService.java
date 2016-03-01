package com.spider.service;

import com.spider.model.JsonResult;

/**
 * Created by wsy on 2015/10/21.
 */
public interface SbcService {

    JsonResult sync(String matchCode, Integer type);

    JsonResult syncOdds(String id);
}
