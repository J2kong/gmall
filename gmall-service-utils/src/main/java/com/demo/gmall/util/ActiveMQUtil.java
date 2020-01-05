package com.demo.gmall.util;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;

import javax.jms.ConnectionFactory;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/13 20:28
 **/

public class ActiveMQUtil {
    PooledConnectionFactory pooledConnectionFactory = null;

    //连接池
    public ConnectionFactory init(String brokerURL) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
        pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);

        pooledConnectionFactory.setReconnectOnException(true);
        pooledConnectionFactory.setMaxConnections(5);
        pooledConnectionFactory.setExpiryTimeout(10000);
        return pooledConnectionFactory;

    }

    public ConnectionFactory getConnectionFactory(){
        return pooledConnectionFactory;
    }
}
