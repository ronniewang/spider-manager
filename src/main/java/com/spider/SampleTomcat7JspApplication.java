package com.spider;

import com.spider.config.DatabaseConfig;
import com.spider.utils.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@Import(value = {DatabaseConfig.class})
public class SampleTomcat7JspApplication extends SpringBootServletInitializer {

    @Autowired
    private Environment environment;

    @Bean
    public MessageSender messageSender() {

        return new MessageSender(environment.getProperty("inplay.odds.group"), environment.getProperty("rocket.mq.addr"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(SampleTomcat7JspApplication.class);
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SampleTomcat7JspApplication.class, args);
    }

}
