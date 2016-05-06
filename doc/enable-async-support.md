# 为Spring MVC开启异步任务

## 配置自定义AsyncTaskExecutor

```java
package com.spider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class AsyncConfig extends WebMvcConfigurationSupport {

    public ThreadPoolTaskExecutor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("ronnie-task-");
        executor.initialize();
        return executor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

        configurer.setTaskExecutor(getAsyncExecutor());
        super.configureAsyncSupport(configurer);
    }
}
```

## 开启日志DEGUB级别

在application.properties中加入

```
logging.level.org.springframework.web=DEBUG
```

## 定义一个Service用来测试

```java
package com.spider.manager.service;

import com.spider.manager.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubLookupService {

    RestTemplate restTemplate = new RestTemplate();

    public User findUser(String user) throws InterruptedException {

        System.out.println("Looking up " + user);
        User results = restTemplate.getForObject("https://api.github.com/users/" + user, User.class);
        Thread.sleep(5000L);//sleep 5s，可以更显著的看到效果
        return results;
    }

}
```

## 定义一个Controller处理请求

```java
package com.spider.manager.controller;

import com.spider.manager.model.User;
import com.spider.manager.service.GitHubLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.concurrent.Callable;

@Controller
public class RobotController {

    @Autowired
    private GitHubLookupService gitHubLookupService;

    @RequestMapping(value = "/async", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Callable<User> async() {

        return new Callable<User>() {

            @Override
            public User call() throws Exception {

                return gitHubLookupService.findUser("CloudFoundry");
            }
        };
    }
}
```

## 启动服务，从浏览器访问

我的地址是<http://localhost:8081/spider-web/async>

大约5s后返回如下结果

{"name":"Cloud Foundry","blog":"https://www.cloudfoundry.org/"}

## 查看日志

```
2016-05-06 14:34:30.097 DEBUG 12004 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : DispatcherServlet with name 'dispatcherServlet' processing GET request for [/spider-web/async]
2016-05-06 14:34:30.102 DEBUG 12004 --- [nio-8081-exec-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Looking up handler method for path /async
2016-05-06 14:34:30.106 DEBUG 12004 --- [nio-8081-exec-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Returning handler method [public java.util.concurrent.Callable<com.spider.manager.model.User> com.spider.manager.controller.RobotController.testPerformance(javax.servlet.http.HttpSession)]
2016-05-06 14:34:30.107 DEBUG 12004 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Last-Modified value for [/spider-web/async] is: -1
2016-05-06 14:34:30.133 DEBUG 12004 --- [nio-8081-exec-1] o.s.w.c.request.async.WebAsyncManager    : Concurrent handling starting for GET [/spider-web/async]
2016-05-06 14:34:30.135 DEBUG 12004 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Leaving response open for concurrent processing
Looking up CloudFoundry
2016-05-06 14:34:30.158 DEBUG 12004 --- [      MvcAsync1] o.s.web.client.RestTemplate              : Created GET request for "https://api.github.com/users/ronniewang"
2016-05-06 14:34:30.203 DEBUG 12004 --- [      MvcAsync1] o.s.web.client.RestTemplate              : Setting request Accept header to [application/json, application/*+json]
2016-05-06 14:34:33.691 DEBUG 12004 --- [      MvcAsync1] o.s.web.client.RestTemplate              : GET request for "https://api.github.com/users/ronniewang" resulted in 200 (OK)
2016-05-06 14:34:33.692 DEBUG 12004 --- [      MvcAsync1] o.s.web.client.RestTemplate              : Reading [class com.spider.manager.model.User] as "application/json;charset=utf-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@6b4d3f3f]
2016-05-06 14:34:38.713 DEBUG 12004 --- [      MvcAsync1] o.s.w.c.request.async.WebAsyncManager    : Concurrent result value [User [name=Ronnie Wang, blog=http://blog.csdn.net/ro_wsy]] - dispatching request to resume processing
2016-05-06 14:34:38.719 DEBUG 12004 --- [nio-8081-exec-2] o.s.web.servlet.DispatcherServlet        : DispatcherServlet with name 'dispatcherServlet' resumed processing GET request for [/spider-web/async]
2016-05-06 14:34:38.719 DEBUG 12004 --- [nio-8081-exec-2] s.w.s.m.m.a.RequestMappingHandlerMapping : Looking up handler method for path /async
2016-05-06 14:34:38.719 DEBUG 12004 --- [nio-8081-exec-2] s.w.s.m.m.a.RequestMappingHandlerMapping : Returning handler method [public java.util.concurrent.Callable<com.spider.manager.model.User> com.spider.manager.controller.RobotController.testPerformance(javax.servlet.http.HttpSession)]
2016-05-06 14:34:38.719 DEBUG 12004 --- [nio-8081-exec-2] o.s.web.servlet.DispatcherServlet        : Last-Modified value for [/spider-web/async] is: -1
2016-05-06 14:34:38.720 DEBUG 12004 --- [nio-8081-exec-2] s.w.s.m.m.a.RequestMappingHandlerAdapter : Found concurrent result value [User [name=Ronnie Wang, blog=http://blog.csdn.net/ro_wsy]]
2016-05-06 14:34:38.761 DEBUG 12004 --- [nio-8081-exec-2] m.m.a.RequestResponseBodyMethodProcessor : Written [User [name=Ronnie Wang, blog=http://blog.csdn.net/ro_wsy]] as "application/json" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@2fdcfec0]
2016-05-06 14:34:38.761 DEBUG 12004 --- [nio-8081-exec-2] o.s.web.servlet.DispatcherServlet        : Null ModelAndView returned to DispatcherServlet with name 'dispatcherServlet': assuming HandlerAdapter completed request handling
2016-05-06 14:34:38.761 DEBUG 12004 --- [nio-8081-exec-2] o.s.web.servlet.DispatcherServlet        : Successfully completed request
```

可以看出其中涉及3个线程，确实实现了异步调用

## 更多信息

关于异步任务的更多解释，可参考下面的文章

<https://spring.io/blog/2012/05/10/spring-mvc-3-2-preview-making-a-controller-method-asynchronous/>
<https://spring.io/blog/2012/05/07/spring-mvc-3-2-preview-introducing-servlet-3-async-support>
