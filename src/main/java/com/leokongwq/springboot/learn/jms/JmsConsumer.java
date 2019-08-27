package com.leokongwq.springboot.learn.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author kongwenqiang
 */
@Component
public class JmsConsumer {

    @JmsListener(containerFactory = "jmsListenerContainerFactory", destination = "hello-jms")
    public void process(String msg) {
        System.out.println("############# Received message is : [" + msg + "]*************");
    }
}
