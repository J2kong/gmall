package com.dmeo.gmall.cart.mq;

import com.demo.gmall.service.CartService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.io.PipedReader;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/17 21:17
 **/
@Component
public class CartMQListener {

    @Reference
    private CartService cartService;

    @JmsListener(destination = "USER_LOGIN_TOPIC")
    public void mergeCookieAndDBCartList(MapMessage mapMessage) {
        String memberId = null;
        try {
            memberId = mapMessage.getString("memberId");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        cartService.mergeCookieAndDBCartList(memberId);
    }

}





