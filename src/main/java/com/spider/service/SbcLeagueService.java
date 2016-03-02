package com.spider.service;

import com.spider.db.entity.TCrawlerSporttery;

public interface SbcLeagueService {

    /**
     * 查找对应的sbc league
     * 
     * @param sporttery
     * @return 查不到或者数据库异常返回sporttery的名称，否则返回我们的名称
     */
    String getLeagueName(TCrawlerSporttery sporttery);
}
