package com.spider.utils;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.spider.global.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 向队列发送更新赔率信息
 *
 * @author wsy
 */
@Component
public class MessageSender {

    private static Logger infoLogger = LogHelper.getInfoLogger();

    private static List<DefaultMQProducer> defaultMQProducers = new ArrayList<>();

    static {
        for (int i = 0; i < Constants.ROCKET_MQ_ADDRS.length; i++) {
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
            defaultMQProducer.setProducerGroup(Constants.ROCKET_MQ_GROUPS[i]);
            defaultMQProducer.setNamesrvAddr(Constants.ROCKET_MQ_ADDRS[i]);
            defaultMQProducers.add(defaultMQProducer);
        }
        try {
            for (DefaultMQProducer mqProducer : defaultMQProducers) {
                mqProducer.start();
            }
        } catch (MQClientException e) {
            throw new IllegalStateException("rocket mq producer start failed", e);
        }
    }

    private MessageSender() {

    }

    public <T> void sendObjectMessage(T object, String producerGroup, String topic, String tag) {

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
            // ignored
        }
        try {
            for (DefaultMQProducer defaultMQProducer : defaultMQProducers) {
                defaultMQProducer.setProducerGroup(producerGroup);
                SendResult sendResult = defaultMQProducer.send(message);
                infoLogger.info("send " + object + " to " + defaultMQProducer.getNamesrvAddr() + ", " + sendResult);
            }
        } catch (Exception e) {
            infoLogger.error("send " + object + " failed", e);
        }
    }

    @PreDestroy
    public void preDestroy() {

        for (DefaultMQProducer defaultMQProducer : defaultMQProducers) {
            defaultMQProducer.shutdown();
        }
    }
}
