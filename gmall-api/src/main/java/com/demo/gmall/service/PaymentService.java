package com.demo.gmall.service;

import com.demo.gmall.bean.PaymentInfo;

import java.util.Map;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/11 20:01
 **/
public interface PaymentService {
    void savePaymentInfo(PaymentInfo paymentInfo);

    int checkPaymentBeforeUpdate(PaymentInfo paymentInfo);

    void updatePayment(PaymentInfo paymentInfo);

    void sendDelayPaymentResultCheckQueue(String outTradeNo,int count);

    Map<String, Object> checkAlipayPayment(String out_trade_no);
}
