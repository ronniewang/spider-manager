package com.spider.manager.sbc;

import com.spider.exception.UpdateException;

/**
 * Created by wsy on 2015/10/14.
 */
public interface SbcUpdateManager {

    <T> void update(T data, String tag) throws UpdateException;

    <T> void update(T data, String tag, String topic) throws UpdateException;

    String getHdcTag();

    String getHiloTag();

    String getScoreAndHalfTag();

    String getInplayParameterTopic();

    String getInplayOddsTopic();
}
