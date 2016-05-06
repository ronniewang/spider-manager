package com.spider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by ronnie on 2016/5/6.
 *
 * @author ronnie
 */
@Configuration
public class AsyncConfig extends WebMvcConfigurationSupport {

    public ThreadPoolTaskExecutor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("spider-task-");
        executor.initialize();
        return executor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

        configurer.setTaskExecutor(getAsyncExecutor());
        super.configureAsyncSupport(configurer);
    }
}
