package com.leokongwq.springboot.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author kongwenqiang
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, ActiveMQAutoConfiguration.class})
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
