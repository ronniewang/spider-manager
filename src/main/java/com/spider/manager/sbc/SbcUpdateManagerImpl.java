package com.spider.manager.sbc;

import com.spider.exception.UpdateException;
import com.spider.utils.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wsy on 2015/10/13.
 *
 * @author ronnie
 */
@Component
public class SbcUpdateManagerImpl implements SbcUpdateManager {

    @Value("${inplay.odds.topic}")
    private String inplayOddsTopic;

    @Autowired
    private MessageSender messageSender;

    /**
     * 默认发往inplayOddsTopic
     *
     * @param data
     * @param tag
     * @param <T>
     * @throws UpdateException
     */
    @Override
    public <T> void update(T data, String tag) throws UpdateException {

        try {
            messageSender.sendObjectMessage(data, inplayOddsTopic, tag);
        } catch (Exception e) {
            throw new UpdateException(e);
        }
    }

    @Override
    public <T> void update(T data, String tag, String topic) throws UpdateException {

        try {
            messageSender.sendObjectMessage(data, topic, tag);
        } catch (Exception e) {
            throw new UpdateException(e);
        }
    }
}
