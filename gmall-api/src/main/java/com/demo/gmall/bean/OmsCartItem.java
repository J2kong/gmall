package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/27 15:43
 **/
@Data
public class OmsCartItem {

    private String id;
    private String productId;
    private String productSkuId;
    private String memberId;
    private BigDecimal quantity;
    // 商品价格
    private BigDecimal price;
    private String sp1;
    private String sp2;
    private String sp3;
    private String productPic;
    private String productName;
    private String productSubTitle;
    private String productSkuCode;
    private String memberNickname;
    private Date createDate;
    private Date modifyDate;
    private int deleteStatus;
    private String productCategoryId;
    private String productBrand;
    private String productSn;
    private String productAttr;
    private String isChecked;

    @Transient
    private BigDecimal totalPrice;






}
