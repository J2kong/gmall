package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/11 19:55
 **/
@Data
public class PaymentInfo {
    @Id
    @Column
    private String  id;

    @Column
    private String orderSn;

    @Column
    private String orderId;

    @Column
    private String alipayTradeNo;

    @Column
    private BigDecimal totalAmount;

    @Column
    private String Subject;

    @Column
    private String paymentStatus;

    @Column
    private Date createTime;

    @Column
    private Date callbackTime;

    @Column
    private String callbackContent;

}
