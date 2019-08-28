package com.leokongwq.springboot.learn.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Session;

/**
 * 自定义配置
 * @author kongwenqiang
 */
@EnableConfigurationProperties(MyActiveMQProperties.class)
@EnableJms
@Component
public class Config {

    private static final int MAXIMUM_ACTIVE_SESSION_PER_SESSION = 200;

    private static final int MAXIMUM_CONNECTIONS = 100;

    /**
     * 配置一个带有连接池能力的 ConnectionFactory
     */
    @Bean
    public ConnectionFactory myConnectionFactory(MyActiveMQProperties myActiveMQProperties) {
        String brokerUrl = myActiveMQProperties.getBrokerUrl();
        String user = myActiveMQProperties.getUser();
        String password = myActiveMQProperties.getPassword();
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(user, password, brokerUrl);
        //重投策略，针对重要消息可以保证不丢失消费
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());

        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
        //很重要
        pooledConnectionFactory.setBlockIfSessionPoolIsFull(true);
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(MAXIMUM_ACTIVE_SESSION_PER_SESSION);
        pooledConnectionFactory.setMaxConnections(MAXIMUM_CONNECTIONS);

        pooledConnectionFactory.setIdleTimeout(1000 * 60 * 30);
        pooledConnectionFactory.setExpiryTimeout(0);
        pooledConnectionFactory.setTimeBetweenExpirationCheckMillis(1000 * 60);

        return pooledConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory myJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("5-50");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setRecoveryInterval(1000L);
        factory.setSessionTransacted(false);
        return factory;
    }

    @Bean
    public JmsTemplate myJmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setConnectionFactory(connectionFactory);
        //客户端签收模式
        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return jmsTemplate;
    }

    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //延迟5s后开始第一次重新投递
        redeliveryPolicy.setInitialRedeliveryDelay(1000);
        //每次重新投递的时间间隔 5s
        redeliveryPolicy.setRedeliveryDelay(5000);
        //重新投递最大延迟时间 只有UseExponentialBackOff(true)为true时生效
        // redeliveryPolicy.setMaximumRedeliveryDelay(60000);
        //一直重新发送
        redeliveryPolicy.setMaximumRedeliveries(-1);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        return redeliveryPolicy;
    }
}
