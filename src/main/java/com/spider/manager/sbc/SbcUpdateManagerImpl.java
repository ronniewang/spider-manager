package com.spider.manager.sbc;

import com.spider.exception.UpdateException;
import com.spider.utils.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wsy on 2015/10/13.
 */
@Component
public class SbcUpdateManagerImpl implements SbcUpdateManager {

    @Value("${inplay.odds.topic}")
    private String inplayOddsTopic;

    @Value("${inplay.odds.topic.parameter}")
    private String inplayParameterTopic;

    @Value("${inplay.odds.hdc.tag}")
    private String hdcTag;

    @Value("${inplay.odds.hilo.tag}")
    private String hiloTag;

    @Value("${inplay.odds.score_half.tag}")
    private String scoreAndHalfTag;

    @Autowired
    private MessageSender messageSender;

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

    @Override
    public String getHdcTag() {

        return hdcTag;
    }

    @Override
    public String getHiloTag() {

        return hiloTag;
    }

    @Override
    public String getScoreAndHalfTag() {

        return scoreAndHalfTag;
    }

    @Override
    public String getInplayParameterTopic() {

        return inplayParameterTopic;
    }

    @Override
    public String getInplayOddsTopic() {

        return inplayOddsTopic;
    }

}
