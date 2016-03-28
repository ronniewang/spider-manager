package com.spider.utils;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.aliyun.openservices.ons.api.Producer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.Serializable;

/**
 * 向队列发送更新赔率信息
 *
 * @author wsy
 */
@Component
public class MessageSender {

    private static Logger infoLogger = LogHelper.getInfoLogger();

    MQProducer mqProducer = null;

    Producer onsProducer = null;

    public MessageSender(MQProducer mqProducer) {

        this.mqProducer = mqProducer;
    }

    public MessageSender(Producer onsProducer) {

        this.onsProducer = onsProducer;
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
            onsProducer.start();
        }
    }

    public <T> void sendObjectMessage(T object, String topic, String tag) {


        try {
            if (mqProducer != null) {
                Message message = new Message();
                message.setTopic(topic);
                message.setTags(tag);
                try {
                    if (object instanceof String) {
                        message.setBody(((String) object).getBytes());
                    } else if (object instanceof Serializable) {
                        message.setBody(IoUtils.objectToBtyeArray((Serializable) object));
                    } else {
                        throw new IllegalArgumentException("not implements Serivalizable");
                    }
                } catch (IOException e) {
                    infoLogger.error(e.getMessage(), e);
                    throw new IllegalStateException("io exception", e);
                }
                SendResult sendResult = mqProducer.send(message);
                infoLogger.info("send " + object + sendResult);
            } else {
                com.aliyun.openservices.ons.api.Message messageOns = new com.aliyun.openservices.ons.api.Message();
                messageOns.setTopic(topic);
                messageOns.setTag(tag);
                if (object instanceof String) {
                    messageOns.setBody(((String) object).getBytes());
                } else if (object instanceof Serializable) {
                    messageOns.setBody(IoUtils.objectToBtyeArray((Serializable) object));
                } else {
                    throw new IllegalArgumentException("not implements Serivalizable");
                }
                com.aliyun.openservices.ons.api.SendResult sendResultOns = onsProducer.send(messageOns);
                infoLogger.info("send " + object + sendResultOns);
            }
        } catch (Exception e) {
            infoLogger.error("send " + object + " failed", e);
            throw new IllegalStateException("mq exception", e);
        }
    }

    @PreDestroy
    public void preDestroy() {

        mqProducer.shutdown();
    }
}
