package com.scheduler.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cloud4u on 2016-04-05.
 */
@Component
@Data
@ConfigurationProperties(locations = {"application.yml"})
public class ConfigProperties {

    @NestedConfigurationProperty

    private Map<String, Object> dataSource = new HashMap<String, Object>();
    private Map<String, Object> mybatis =  new HashMap<String, Object>();
    private Map<String, Object> mail =  new HashMap<String, Object>();

}
