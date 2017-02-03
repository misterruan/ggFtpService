package com.ytx.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by rock on 2016/12/28.
 * 项目配置缓存专用service
 */
@Component
public class SystemConfigService {
    private static final Log logger = LogFactory.getLog(SystemConfigService.class);
    private Properties properties = new Properties();

    //构造方法
    public SystemConfigService() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(SystemConfigService.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8");
            this.properties.load(inputStreamReader);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("读取配置文件configure.properties失败！");
        }

    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        String value = this.properties.getProperty(key);
        return value == null?defaultValue:value;
    }


}
