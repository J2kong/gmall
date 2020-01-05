package com.demo.gmall.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.demo.gmall.bean.OmsOrder;
import com.demo.gmall.bean.OmsOrderItem;
import com.demo.gmall.mapper.OmsOrderItemMapper;
import com.demo.gmall.mapper.OmsOrderMapper;
import com.demo.gmall.service.CartService;
import com.demo.gmall.service.OrderService;
import com.demo.gmall.util.ActiveMQUtil;
import com.demo.gmall.util.RedisUtil;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import javax.jms.*;
import java.io.PipedReader;
import java.util.List;
import java.util.UUID;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/6 20:15
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ActiveMQUtil activeMQUtil;

    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    @Reference
    private CartService cartService;


    @Override
    public String checkTradeCode(String memberId, String tradeCode) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String tradeKey = "user:" + memberId + ":tradeCode";
            String tradeCodeFromCache = jedis.get(tradeKey);
            //lua脚本解决并发问题
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Long eval = (Long) jedis.eval(script);
            if (eval != null && eval != 0) {
                return "success";
            } else {
                return "fail";
            }
        } finally {
            jedis.close();
        }

    }

    @Override
    public void saveOrder(OmsOrder omsOrder) {
        omsOrderMapper.insertSelective(omsOrder);
        String orderId = omsOrder.getId();

        List<OmsOrderItem> omsOrderItems = omsOrder.getOmsOrderItems();
        for (OmsOrderItem omsOrderItem : omsOrderItems) {
            omsOrderItem.setOrderId(orderId);
            omsOrderItemMapper.insertSelective(omsOrderItem);
            cartService.delCart(omsOrder.getMemberId(), omsOrderItem.getProductSkuId());
        }

    }

    @Override
    public String genTradeCode(String memberId) {
        Jedis jedis = null;
        String tradeCode;
        try {
            jedis = redisUtil.getJedis();
            tradeCode = UUID.randomUUID().toString();
            jedis.setex("user:" + memberId + ":tradeCode", 60 * 15, tradeCode);
        } finally {
            jedis.close();
        }

        return tradeCode;
    }

    @Override
    public OmsOrder getOrderByOutTradeNo(String outTradeNo) {
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setOrderSn(outTradeNo);
        return omsOrderMapper.selectOne(omsOrder);
    }

    @Override
    public void updateOrder(OmsOrder omsOrder) {
        Example example = new Example(OmsOrder.class);
        example.createCriteria().andEqualTo("orderSn", omsOrder.getOrderSn());
        omsOrder.setStatus("1");

        Connection connection = null;
        Session session = null;
        try {
            connection = activeMQUtil.getConnectionFactory().createConnection();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Queue payhment_success_queue = session.createQueue("ORDER_PAY_QUEUE");
            MessageProducer producer = session.createProducer(payhment_success_queue);
            MapMessage MapMessage = new ActiveMQMapMessage();
            //  activeMQMapMessage.setString("");
            omsOrderMapper.updateByExampleSelective(omsOrder, example);
            session.commit();
        } catch (JMSException e) {
            try {
                session.rollback();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateOrderToDeduct(OmsOrder omsOrder){
        omsOrderMapper.updateByPrimaryKeySelective(omsOrder);
    }
}
