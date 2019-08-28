package com.leokongwq.springboot.learn.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.data.web.config.HateoasAwareSpringDataWebConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author kongwenqiang
 */
@Configuration
public class WebConfig extends HateoasAwareSpringDataWebConfiguration {

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
//        formatterRegistry.addConverter(new MyConverter());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
        argumentResolvers.add(new SortHandlerMethodArgumentResolver());
    }
}
