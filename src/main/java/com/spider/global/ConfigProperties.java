package com.spider.global;

import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 加载配置文件中的所有配置，统一管理应用的属性，获取配置时调用静态的$方法获取属性值
 * 
 * @author wsy
 *
 */
public final class ConfigProperties {

    private static Logger logger = Logger.getLogger(ConfigProperties.class);

    private static Map<String, String> propertiesMap = new HashMap<>();
    static {
        Properties properties = new Properties();
        try {
            properties.load(new DefaultResourceLoader().getResource("classpath:application.properties").getInputStream());
        } catch (IOException e) {
            logger.fatal("配置文件读取异常");
            throw new RuntimeException("配置文件读取异常");
        }
        Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = it.next();
            propertiesMap.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    /**
     * 获取相应key值对应的属性值
     * 
     * @param key
     * @return
     */
    public static String $(String key) {

        return propertiesMap.get(key);
    }

    private ConfigProperties() {

    }
}
