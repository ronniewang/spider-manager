package com.spider;

import com.spider.config.DatabaseConfig;
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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@Import(value = {DatabaseConfig.class})
//@EnableSolrRepositories("com.spider.solr.repository")
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

//    @Bean
//    public SolrTemplate solrTemplate() throws Exception {
//        return new SolrTemplate(solrServerFactoryBean().getObject());
//    }

    @Bean
    public MessageSender messageSender() {

        return new MessageSender(environment.getProperty("inplay.odds.group"), environment.getProperty("rocket.mq.addr"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(SpiderManagerApplication.class);
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SpiderManagerApplication.class, args);
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

//            http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
//                    .loginPage("/login").failureUrl("/login?error").permitAll().and()
//                    .logout().permitAll();
            http.authorizeRequests().antMatchers("/css/**", "/js/**", "/image/**").permitAll().anyRequest()
                    .fullyAuthenticated().and().formLogin().loginPage("/login")
                    .failureUrl("/login?error=1").permitAll().and().logout().permitAll();
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.inMemoryAuthentication().withUser("caiex").password("caiex")
                    .roles("ADMIN", "USER").and().withUser("user").password("user")
                    .roles("USER");
        }

    }
}
