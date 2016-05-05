package com.spider.manager.sbc;

import com.spider.exception.UpdateException;

/**
 * Created by wsy on 2015/10/14.
 * <p>
 * 管理向sbc发送消息的元数据，包括topic，tag等
 *
 * @author ronnie
 * @see SbcUpdateManagerImpl
 */
public interface SbcUpdateManager {

    /**
     * 默认发送到inplay.odds.topic
     *
     * @param data
     * @param tag
     * @param <T>
     * @throws UpdateException
     */
    <T> void update(T data, String tag) throws UpdateException;

    <T> void update(T data, String tag, String topic) throws UpdateException;

    String getHdcTag();

    String getHiloTag();

    String getScoreAndHalfTag();

    String getInplayParameterTopic();

    String getInplayOddsTopic();
}
