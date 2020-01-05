package com.demo.gmall.service;

import com.demo.gmall.bean.OmsCartItem;
import com.demo.gmall.bean.UmsMember;
import com.demo.gmall.bean.UmsMemberReceiveAddress;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/23 14:44
 **/

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    UmsMember login(UmsMember umsMember);

    void addUserToken(String token, String memberId);

    UmsMember checkOauthUser(UmsMember umsCheck);

    UmsMember addOauthUser(UmsMember umsMember);

    BigDecimal getTotalAmount(List<OmsCartItem> omsCartItems);

    UmsMemberReceiveAddress getReceiveAddressById(String receiveAddressId);

    void sendUserLoginTopic(String memberId, String nickname);
}
