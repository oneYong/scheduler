package com.scheduler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by WYKIM on 2017-06-19.
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = {"com.center.dashboard"}, useDefaultFilters = false,
        includeFilters = { @ComponentScan.Filter(Controller.class),
                @ComponentScan.Filter(ControllerAdvice.class),
                @ComponentScan.Filter(Repository.class),
                @ComponentScan.Filter(Component.class),
                @ComponentScan.Filter(Service.class)})
public class AppConfig extends WebMvcConfigurerAdapter {


}
