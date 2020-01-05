package com.demo.gmall.search.mq;

import com.demo.gmall.service.SearchService;
import com.demo.gmall.service.SkuService;
import com.demo.gmall.util.ActiveMQUtil;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/17 20:49
 **/
@Component
public class SkuInfoHotScoreMQListener {

    @Reference
    private SearchService searchService;
    @Autowired
    private ActiveMQUtil activeMQUtil;

    @JmsListener(destination = "HOTSCORE_FLUSH_QUEUE", containerFactory = "jmsQueueListener")
    public void listenToFlushHotScore(MapMessage mapMessage) {
        String skuId = null;
        try {
            skuId = mapMessage.getString("skuId");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        searchService.incrHotScore(skuId);
    }
}
