package com.spider;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.spider.config.DatabaseConfig;
import com.spider.config.WebSocketConfig;
import com.spider.utils.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactoryBean;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;

import java.util.Properties;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@Import(value = {DatabaseConfig.class, WebSocketConfig.class})
@EnableSolrRepositories("com.spider.solr.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpiderManagerApplication extends SpringBootServletInitializer {

    @Autowired
    private Environment environment;

//    @Bean
//    public EmbeddedSolrServerFactoryBean solrServerFactoryBean() {
//        EmbeddedSolrServerFactoryBean factory = new EmbeddedSolrServerFactoryBean();
//
//        factory.setSolrHome(environment.getRequiredProperty("solr.solr.home"));
//
//        return factory;
//    }

    @Bean
    public HttpSolrServerFactoryBean solrServerFactoryBean() {

        HttpSolrServerFactoryBean factory = new HttpSolrServerFactoryBean();

        factory.setUrl(environment.getRequiredProperty("solr.server.url"));

        return factory;
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {

        return new SolrTemplate(solrServerFactoryBean().getObject());
    }

    @Bean
    public MessageSender messageSender() {

        String isOns = environment.getProperty("mq.is.ons");
        if (isOns.equals("no")) {
            String producerGroup = environment.getProperty("inplay.odds.group");
            String mqServerAddress = environment.getProperty("rocket.mq.addr");
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
            defaultMQProducer.setProducerGroup(producerGroup);
            defaultMQProducer.setNamesrvAddr(mqServerAddress);
            return new MessageSender(defaultMQProducer);
        } else if (isOns.equals("yes")) {
            String produceId = environment.getProperty("mq.ons.producerId.inplayParameter");
            String accessKey = environment.getProperty("mq.ons.accessKey.inplayParameter");
            String secretKey = environment.getProperty("mq.ons.secretKey.inplayParameter");
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.ProducerId, produceId);
            properties.put(PropertyKeyConst.AccessKey, accessKey);
            properties.put(PropertyKeyConst.SecretKey, secretKey);
            return new MessageSender(ONSFactory.createProducer(properties));
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

//    @Configuration
//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//
//            http.authorizeRequests().anyRequest().permitAll();
//            http.authorizeRequests().antMatchers("/css/**", "/js/**", "/image/**").permitAll().anyRequest()
//                    .fullyAuthenticated().and().formLogin().loginPage("/login")
//                    .failureUrl("/login?error=1").permitAll().and().logout().permitAll();
//        }
//
//        @Override
//        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//            auth.inMemoryAuthentication().withUser("caiex").password("caiex")
//                    .roles("ADMIN", "USER").and().withUser("user").password("user")
//                    .roles("USER");
//        }
//
//    }
}
