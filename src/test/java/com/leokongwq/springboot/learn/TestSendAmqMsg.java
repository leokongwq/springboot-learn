package com.leokongwq.springboot.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = {"dev"})
public class TestSendAmqMsg {

    @Resource
    private JmsTemplate jmsTemplate;

    @Resource(name = "myJmsTemplate")
    private JmsTemplate myJmsTemplate;

    @Test
    public void testSendMsgDefault() throws Exception {
        //默认发送到的Destination 是 topic, 设置false表示发送到 queue
        jmsTemplate.setPubSubDomain(false);
        jmsTemplate.convertAndSend("java");

        Thread.sleep(1000 * 20);
    }

    @Test
    public void testSendMsgByMyJmsTemplate() throws Exception {
        myJmsTemplate.setDefaultDestinationName("hello-jms");
        //默认发送到的Destination 是 topic, 设置false表示发送到 queue
        myJmsTemplate.setPubSubDomain(false);
        myJmsTemplate.convertAndSend("java");

        Thread.sleep(1000 * 20);
    }
}
