package com.demo.gmall.service;

import com.demo.gmall.bean.OmsOrder;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/6 20:15
 **/
public interface OrderService {
    String checkTradeCode(String memberId, String tradeCode);

    void saveOrder(OmsOrder omsOrder);

    String genTradeCode(String memberId);

    OmsOrder getOrderByOutTradeNo(String outTradeNo);

    void updateOrder(OmsOrder omsOrder);

    void updateOrderToDeduct(OmsOrder omsOrder);
}
