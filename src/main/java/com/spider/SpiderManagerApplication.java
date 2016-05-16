package com.spider;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.spider.config.DatabaseConfig;
import com.spider.config.WebSocketConfig;
import com.spider.utils.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Properties;

/**
 * @author ronnie
 */
@SpringBootApplication
@Import(value = {DatabaseConfig.class, WebSocketConfig.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class SpiderManagerApplication extends SpringBootServletInitializer {

    @Autowired
    private Environment environment;

    @Bean
    public static JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp-mail.outlook.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("ronniewang1993@outlook.com");
        javaMailSender.setPassword("1b3456789!");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }

    @Bean
    public MessageSender messageSender() {

        String isOns = environment.getProperty("mq.is.ons");
        if ("no".equals(isOns)) {
            String producerGroup = environment.getProperty("inplay.odds.group");
            String mqServerAddress = environment.getProperty("rocket.mq.addr");
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
            defaultMQProducer.setProducerGroup(producerGroup);
            defaultMQProducer.setNamesrvAddr(mqServerAddress);
            return new MessageSender(defaultMQProducer);
        } else if ("yes".equals(isOns)) {
            String accessKey = environment.getProperty("mq.ons.accessKey");
            String secretKey = environment.getProperty("mq.ons.secretKey");

            String produceOddsId = environment.getProperty("mq.ons.producerId.odds");
            Properties oddsProperties = new Properties();
            oddsProperties.put(PropertyKeyConst.ProducerId, produceOddsId);
            oddsProperties.put(PropertyKeyConst.AccessKey, accessKey);
            oddsProperties.put(PropertyKeyConst.SecretKey, secretKey);
            Producer onsOddsProducer = ONSFactory.createProducer(oddsProperties);

            String produceParameterId = environment.getProperty("mq.ons.producerId.inplayParameter");
            Properties parameterProperties = new Properties();
            parameterProperties.put(PropertyKeyConst.ProducerId, produceParameterId);
            parameterProperties.put(PropertyKeyConst.AccessKey, accessKey);
            parameterProperties.put(PropertyKeyConst.SecretKey, secretKey);
            Producer onsParameterProducer = ONSFactory.createProducer(parameterProperties);

            return new MessageSender(onsOddsProducer, onsParameterProducer);
        }
        throw new NullPointerException("no specified mq producer");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(SpiderManagerApplication.class);
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SpiderManagerApplication.class, args);
    }

}
