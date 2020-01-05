package com.demo.gware.util;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.codehaus.groovy.runtime.ArrayUtil;

import javax.jms.Connection;
import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * @param
 * @return
 */
public class ActiveMQUtil {

    PooledConnectionFactory pooledConnectionFactory = null;


    public void init(String brokerUrl) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setExpiryTimeout(2000);
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(10);
        pooledConnectionFactory.setMaxConnections(30);
        pooledConnectionFactory.setReconnectOnException(true);


    }

    public Connection getConnection() {

        Connection connection = null;
        try {
            connection = pooledConnectionFactory.createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void main(String[] args) {
        int n = 101;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = i + 1;
        }
        arr[arr.length - 1] = new Random().nextInt(n - 1) + 1;
        int index = new Random().nextInt(n);
        arr[arr.length - 1] = arr[arr.length - 1] + arr[index];
        arr[index] = arr[arr.length - 1] - arr[index];
        arr[arr.length - 1] = arr[arr.length - 1] - arr[index];
        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 1; i <= n - 1; i++) {
            a = a^i;
        }
        for (int i = 0; i < n; i++) {
            a = a^arr[i];
        }
        System.out.println(a);

        System.out.println(System.currentTimeMillis()-start);

    }


}
