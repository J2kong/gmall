package com.demo.gmall.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/6 17:51
 **/
@Data
public class OmsOrderItem implements Serializable {

    private String id;
    private String orderId;
    private String orderSn;
    private String productId;
    private String productPic;
    private String productName;
    private String productBrand;
    private String productSn;
    private BigDecimal productPrice;
    private BigDecimal productQuantity;
    private String productSkuId;
    private String productSkuCode;
    private String productCategoryId;
    private String sp1;
    private String sp2;
    private String sp3;
    private String promotionName;
    private BigDecimal promotionAmount;
    private BigDecimal couponAmount;
    private BigDecimal integrationAmount;
    private BigDecimal realAmount;
    private int giftIntegration;
    private int giftGrowth;
    private String productAttr;
}
