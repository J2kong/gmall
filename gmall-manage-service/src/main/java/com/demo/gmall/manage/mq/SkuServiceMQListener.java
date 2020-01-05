package com.demo.gmall.manage.mq;

import com.alibaba.fastjson.JSON;
import com.demo.gmall.bean.PmsSkuInfo;
import com.demo.gmall.service.SearchService;
import com.demo.gmall.service.SkuService;
import com.demo.gmall.util.RedisUtil;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/16 17:41
 **/
@Component
public class SkuServiceMQListener {
    @Reference
    private SkuService skuService;
    @Autowired
    private RedisUtil redisUtil;
    @Reference
    private SearchService searchService;


    @JmsListener(destination = "SKUINFO_FLUSH_QUEUE", containerFactory = "jmsQueueListener")
    public void consumeSkuInfoToFlush(MapMessage mapMessage) {
        String skuId = null;
        try {
            skuId = mapMessage.getString("skuId");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        PmsSkuInfo skuByIdFromDb = skuService.getSkuByIdFromDb(skuId);
        Jedis jedis = redisUtil.getJedis();
        jedis.set("sku:" + skuId + ":info", JSON.toJSONString(skuByIdFromDb));
    }

    @JmsListener(destination = "SEARCH_REFRESH_QUEUE", containerFactory = "jmsQueueListener")
    public void consumeSearchToFlush(MapMessage mapMessage) {
        String skuId = null;
        try {
            skuId = mapMessage.getString("skuId");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        searchService.put(skuId);
    }


}
