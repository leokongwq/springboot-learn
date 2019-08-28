package com.leokongwq.springboot.learn.jms;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author kongwenqiang
 */
@ConfigurationProperties(prefix = "my.activemq")
public class MyActiveMQProperties {

    /**
     * URL of the ActiveMQ broker. Auto-generated by default.
     */
    private String brokerUrl;

    /**
     * Login user of the broker.
     */
    private String user;

    /**
     * Login password of the broker.
     */
    private String password;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}