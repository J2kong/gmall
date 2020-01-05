package com.demo.gmall.mq;

import com.demo.gmall.bean.OmsOrder;
import com.demo.gmall.service.OrderService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/14 19:22
 **/
@Component
public class OrderServiceMqListener {

    @Autowired
    private OrderService orderService;

    @JmsListener(destination = "PAYHMENT_SUCCESS_QUEUE", containerFactory = "jmsQueueListen")
    public void consumePaymentResult(MapMessage mapMessage) {
        String out_trade_no = null;
        try {
            out_trade_no = mapMessage.getString("out_trade_no");
        } catch (JMSException e) {
            e.printStackTrace();
        }

        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setOrderSn(out_trade_no);

        orderService.updateOrder(omsOrder);

    }

    @JmsListener(destination = "SKU_DEDUCT_QUEUE", containerFactory = "jmsQueueListen")
    public void consumeSkuDeduct(MapMessage mapMessage) {
        String status = null;
        String orderId = null;
        try {
            orderId = mapMessage.getString("orderId");
            status = mapMessage.getString("status");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setStatus(status);
        omsOrder.setId(orderId);
        orderService.updateOrderToDeduct(omsOrder);

    }


    @JmsListener(destination = "SKU_DELIVER_QUEUE", containerFactory = "jmsQueueListen")
    public void consumeSkuDeliver(MapMessage mapMessage) {
        String status = null;
        String orderId = null;
        String trackingNo = null;
        String deliveryTime = null;
        try {
            orderId = mapMessage.getString("orderId");
            status = mapMessage.getString("status");
            trackingNo = mapMessage.getString("tracking_no");
            deliveryTime = mapMessage.getString("deliveryTime");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setStatus(status);
        omsOrder.setId(orderId);
        omsOrder.setDeliverySn(trackingNo);
        omsOrder.setDeliveryTime(new Date(deliveryTime));
        orderService.updateOrderToDeduct(omsOrder);

    }

}
