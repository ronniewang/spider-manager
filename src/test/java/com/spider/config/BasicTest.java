package com.spider.config;

import com.spider.SpiderManagerApplication;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by ronniewang on 16/5/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpiderManagerApplication.class})
@WebAppConfiguration
@Ignore
public class BasicTest {
}
