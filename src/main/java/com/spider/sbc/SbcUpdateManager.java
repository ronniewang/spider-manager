package com.spider.sbc;

import com.spider.exception.MqException;

/**
 * Created by wsy on 2015/10/14.
 */
public interface SbcUpdateManager {

    <T> void update(T data, String tag) throws MqException;

    <T> void update(T data, String tag, String topic) throws MqException;

    String getProducerGroup();

    String getInplayTopic();

    String getHdcTag();

    String getHiloTag();

    String getScoreAndHalfTag();

    String getParameterTopic();
}
