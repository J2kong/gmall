package com.demo.gmall.payment.mq;

import com.demo.gmall.bean.PaymentInfo;
import com.demo.gmall.service.PaymentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/15 14:32
 **/
@Component
public class PaymentServiceMQListener {

    @Autowired
    private PaymentService paymentService;

    @JmsListener(destination = "PAYMENT_CHECK_QUEUE", containerFactory = "jmsQueueListener")
    public void consumePaymentCheckResult(MapMessage mapMessage) throws JMSException {
        String out_trade_no = mapMessage.getString("out_trade_no");

        int count = mapMessage.getInt("count");

        //检查支付
        Map<String, Object> resultMap = paymentService.checkAlipayPayment(out_trade_no);
        if (resultMap != null && !resultMap.isEmpty()) {
            String trade_status = (String) resultMap.get("trade_status");

            if ((StringUtils.isNotBlank(trade_status) && trade_status.equals("TRADE_SUCCESS"))) {
                // 支付成功，更新支付发送支付队列
                PaymentInfo paymentInfo = new PaymentInfo();
                paymentInfo.setOrderSn(out_trade_no);
                paymentInfo.setPaymentStatus("已支付");
                paymentInfo.setAlipayTradeNo((String) resultMap.get("trade_no"));// 支付宝的交易凭证号
                paymentInfo.setCallbackContent((String) resultMap.get(("call_back_content")));//回调请求字符串
                paymentInfo.setCallbackTime(new Date());
                paymentService.updatePayment(paymentInfo);
                return;
            }
        }
        if (count > 0) {
            count--;
            paymentService.sendDelayPaymentResultCheckQueue(out_trade_no, count);
        } else {
            //交易关闭 // 跳转回订单提交页面
            return;
        }

    }


}
