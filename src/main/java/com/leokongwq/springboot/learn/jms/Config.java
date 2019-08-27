package com.leokongwq.springboot.learn.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Component;

/**
 * @author kongwenqiang
 */
@EnableJms
@Component
public class Config {

//    @Bean
//    public DefaultJmsListenerContainerFactory myJmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setDestinationResolver(destinationResolver());
//        factory.setConcurrency("5");
//        return factory;
//    }

}
