package com.spider.manager.sbc;

import com.spider.exception.MqException;
import com.spider.utils.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wsy on 2015/10/13.
 */
@Component
public class SbcUpdateManagerImpl implements SbcUpdateManager {

    @Value("${inplay.odds.group}")
    private String producerGroup;

    @Value("${inplay.odds.topic}")
    private String inplayTopic;

    @Value("${inplay.odds.topic.parameter}")
    private String parameterTopic;

    @Value("${inplay.odds.hdc.tag}")
    private String hdcTag;

    @Value("${inplay.odds.hilo.tag}")
    private String hiloTag;

    @Value("${inplay.odds.score_half.tag}")
    private String scoreAndHalfTag;

    @Autowired
    private MessageSender messageSender;

    @Override
    public <T> void update(T data, String tag) throws MqException {

        messageSender.sendObjectMessage(data, producerGroup, inplayTopic, tag);
    }

    @Override
    public <T> void update(T data, String tag, String topic) throws MqException {

        messageSender.sendObjectMessage(data, producerGroup, topic, tag);
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
    public String getParameterTopic() {

        return parameterTopic;
    }
}
