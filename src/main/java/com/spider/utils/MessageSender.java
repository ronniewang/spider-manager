package com.spider.utils;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.spider.SpiderManagerApplication;
import com.spider.manager.sbc.SbcUpdateManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.Serializable;

/**
 * 向队列发送更新赔率信息
 * 包含rocketmq客户端和ons客户端两种producer，根据配置来决定使用哪种producer
 *
 * @author ronnie
 * @see SpiderManagerApplication#messageSender()
 */
@Component
public class MessageSender {

    private static Logger infoLogger = LogHelper.getInfoLogger();

    private MQProducer mqProducer = null;

    private Producer onsOddsProducer = null;

    private Producer onsParameterProducer = null;

    @Autowired
    private SbcUpdateManager sbcUpdateManager;

    public MessageSender(MQProducer mqProducer) {

        this.mqProducer = mqProducer;
    }

    public MessageSender(Producer onsOddsProducer, Producer onsParameterProducer) {

        this.onsOddsProducer = onsOddsProducer;
        this.onsParameterProducer = onsParameterProducer;
    }

    @PostConstruct
    public void postConstruct() {

        if (mqProducer != null) {
            try {
                mqProducer.start();
            } catch (MQClientException e) {
                throw new IllegalStateException("rocket mq producer start failed", e);
            }
        } else {
            onsParameterProducer.start();
            onsOddsProducer.start();
        }
    }

    public <T> void sendObjectMessage(T object, String topic, String tag) {

        try {
            if (mqProducer != null) {
                Message message = new Message();
                message.setTopic(topic);
                message.setTags(tag);
                message.setBody(getBody(object));
                SendResult sendResult = mqProducer.send(message);
                infoLogger.info("send " + object + sendResult);
            } else {
                com.aliyun.openservices.ons.api.Message messageOns = new com.aliyun.openservices.ons.api.Message();
                messageOns.setTopic(topic);
                messageOns.setTag(tag);
                messageOns.setBody(getBody(object));
                com.aliyun.openservices.ons.api.SendResult sendResultOns;
                if (topic.equals(sbcUpdateManager.getInplayOddsTopic())) {
                    sendResultOns = onsOddsProducer.send(messageOns);
                } else {
                    sendResultOns = onsParameterProducer.send(messageOns);
                }
                infoLogger.info("send " + object + sendResultOns);
            }
        } catch (IOException e) {
            infoLogger.error(e.getMessage(), e);
            throw new IllegalStateException("io exception", e);
        } catch (Exception e) {
            infoLogger.error("send " + object + " failed", e);
            throw new IllegalStateException("mq exception", e);
        }
    }

    private <T> byte[] getBody(T object) throws IOException {

        if (object instanceof String) {
            return ((String) object).getBytes();
        } else if (object instanceof Serializable) {
            return IoUtils.objectToBtyeArray((Serializable) object);
        } else {
            throw new IllegalArgumentException("not implements Serivalizable");
        }
    }

    @PreDestroy
    public void preDestroy() {

        mqProducer.shutdown();
    }
}
