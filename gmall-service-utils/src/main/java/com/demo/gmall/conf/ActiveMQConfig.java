package com.demo.gmall.conf;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfigBinding;
import com.demo.gmall.util.ActiveMQUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/13 20:28
 **/
@Configuration
public class ActiveMQConfig {
    @Value("${spring.activemq.broker-url:disabled}")
    private String brokerURL;

    @Value("${activemq.listener.enable:disabled}")
    private String listenerEnable;


    @Bean
    public ActiveMQUtil getActiveMQUtil()throws JMSException {
        if (brokerURL.equals("disabled")) {
            return null;
        }
        ActiveMQUtil activeMQUtil = new ActiveMQUtil();
        activeMQUtil.init(brokerURL);
        return activeMQUtil;
    }

    @Bean(name = "jmsQueueListener")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        if (!listenerEnable.equals("true")) {
            return null;
        }
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
        factory.setConnectionFactory(cachingConnectionFactory);
        //设置并发数
        factory.setConcurrency("5");

        //重连间隔时间
        factory.setRecoveryInterval(5000L);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return factory;
    }

    @Bean(name = "jmsTopicListener")
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
        factory.setConnectionFactory(cachingConnectionFactory);
        //重连间隔时间
        factory.setRecoveryInterval(5000L);
        factory.setPubSubDomain(true);
        factory.setSessionTransacted(true);
        factory.setConcurrency("5");
        //开启持久化订阅
        factory.setSubscriptionDurable(true);
        return factory;
    }


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //定义ReDelivery(重发机制)机制 ，重发时间间隔是100毫秒，最大重发次数是3次
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次   这里设置为1次
        redeliveryPolicy.setMaximumRedeliveries(1);
        //重发时间间隔,默认为1秒
        redeliveryPolicy.setInitialRedeliveryDelay(1000);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        //最大传送延迟，只在useExponentialBackOff为true时有效（V5.5），假设首次重连间隔为10ms，倍数为2，那么第
        //二次重连时间间隔为 20ms，第三次重连时间间隔为40ms，当重连时间间隔大的最大重连时间间隔时，以后每次重连时间间隔都为最大重连时间间隔。
        redeliveryPolicy.setMaximumRedeliveryDelay(1000);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        return activeMQConnectionFactory;
    }


}
