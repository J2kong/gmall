package com.demo.gmall.service;

import com.demo.gmall.bean.OmsCartItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/29 20:53
 **/
public interface CartService  {

    OmsCartItem ifCartExistByUser(String memberId, String skuId);

    void addCart(OmsCartItem omsCartItem);

    void updateCart(OmsCartItem omsCartItemFromDb);

    void flushCartCache(String memberId, List<OmsCartItem> omsCartItemList);

    List<OmsCartItem> cartList(String userId);

    void checkCart(OmsCartItem omsCartItem);

    BigDecimal getTotalAmount(List<OmsCartItem> omsCartItems);

    void delCart(String memberId, String productSkuId);

    void mergeCookieAndDBCartList(String memberId);
}
