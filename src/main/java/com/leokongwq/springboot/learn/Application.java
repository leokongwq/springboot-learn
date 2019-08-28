package com.leokongwq.springboot.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author kongwenqiang
 *
 * 解决jackson 双写关联entity序列化问题：https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
 */
@SpringBootApplication(exclude = {ActiveMQAutoConfiguration.class})
@EnableCaching
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableSpringDataWebSupport
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
