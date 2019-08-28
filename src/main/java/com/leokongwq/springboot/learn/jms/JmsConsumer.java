package com.leokongwq.springboot.learn.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author kongwenqiang
 */
@Component
public class JmsConsumer {

    @JmsListener(containerFactory = "jmsListenerContainerFactory", destination = "hello-jms-default")
    public void process(String msg) {
        System.out.println("############# Received message is : [" + msg + "]*************");
    }

    @JmsListener(containerFactory = "myJmsListenerContainerFactory", destination = "hello-jms")
    public void consumerMsg(String msg) {
        System.out.println("############# Received message is : [" + msg + "]*************");
    }
}
