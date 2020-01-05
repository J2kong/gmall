package com.dmeo.gmall.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.demo.gmall.bean.OmsCartItem;
import com.demo.gmall.service.CartService;
import com.demo.gmall.service.CatalogService;
import com.demo.gmall.util.CookieUtil;
import com.demo.gmall.util.RedisUtil;
import com.dmeo.gmall.cart.mapper.OmsCartItemMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/29 20:53
 **/

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OmsCartItemMapper omsCartItemMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public OmsCartItem ifCartExistByUser(String memberId, String skuId) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        omsCartItem.setProductSkuId(skuId);

        OmsCartItem selectOne = omsCartItemMapper.selectOne(omsCartItem);

        return selectOne;
    }

    @Override
    public void addCart(OmsCartItem omsCartItem) {
        if (StringUtils.isNotBlank(omsCartItem.getMemberId())) {
            omsCartItemMapper.insertSelective(omsCartItem);
        }
    }

    @Override
    public void updateCart(OmsCartItem omsCartItemFromDb) {
        Example example = new Example(OmsCartItem.class);
        example.createCriteria().andEqualTo("id", omsCartItemFromDb.getId());
        omsCartItemMapper.updateByExampleSelective(omsCartItemFromDb, example);
    }

    @Override
    public void flushCartCache(String memberId, List<OmsCartItem> omsCartItemList) {
        List<OmsCartItem> omsCartItems = null;
        if (omsCartItemList == null) {
            OmsCartItem omsCartItem = new OmsCartItem();
            omsCartItem.setMemberId(memberId);
            omsCartItems = omsCartItemMapper.select(omsCartItem);
        }else {
            omsCartItems=omsCartItemList;
        }
        Map<String, String> map = new HashMap<>();
        for (OmsCartItem cartItem : omsCartItems) {
            cartItem.setTotalPrice(cartItem.getPrice().multiply(cartItem.getQuantity()));
            map.put(cartItem.getProductSkuId(), JSON.toJSONString(cartItem));
        }
        Jedis jedis = redisUtil.getJedis();
        jedis.del("user:" + memberId + ":cart");
        jedis.hmset("user:" + memberId + ":cart", map);

        jedis.close();
    }

    @Override
    public List<OmsCartItem> cartList(String userId) {
        Jedis jedis = null;
        List<OmsCartItem> omsCartItems = new ArrayList<>();
        try {
            jedis = redisUtil.getJedis();
            List<String> hvals = jedis.hvals("user:" + userId + ":cart");
            for (String hval : hvals) {
                OmsCartItem item = JSON.parseObject(hval, OmsCartItem.class);
                omsCartItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
        return omsCartItems;
    }

    @Override
    public void checkCart(OmsCartItem omsCartItem) {

        Example e = new Example(OmsCartItem.class);

        e.createCriteria().andEqualTo("memberId", omsCartItem.getMemberId()).andEqualTo("productSkuId", omsCartItem.getProductSkuId());

        omsCartItemMapper.updateByExampleSelective(omsCartItem, e);

        // 缓存同步
        flushCartCache(omsCartItem.getMemberId(),null);
    }

    @Override
    public BigDecimal getTotalAmount(List<OmsCartItem> omsCartItems) {
        BigDecimal toalAmount = new BigDecimal("0");

        for (OmsCartItem omsCartItem : omsCartItems) {

            if (omsCartItem.getIsChecked().equals("1")) {
                toalAmount.add(omsCartItem.getTotalPrice());
            }
        }
        return toalAmount;
    }

    @Override
    public void delCart(String memberId, String productSkuId) {
        OmsCartItem item = new OmsCartItem();
        item.setMemberId(memberId);
        item.setProductSkuId(productSkuId);
        omsCartItemMapper.delete(item);
        flushCartCache(memberId,null);
    }

    @Override
    public void mergeCookieAndDBCartList(String memberId) {
        HttpServletRequest request = null;
        List<OmsCartItem> omsCartItemList = cartList(memberId);
        String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
        List<OmsCartItem> omsCartItems = JSON.parseArray(cartListCookie, OmsCartItem.class);
        for (OmsCartItem dB : omsCartItemList) {
            for (OmsCartItem cookie : omsCartItems) {
                if (dB.getProductSkuId().equals(cookie.getProductSkuId())) {
                    dB.setQuantity(dB.getQuantity().add(cookie.getQuantity()));
                } else {
                    omsCartItemList.add(cookie);
                }
            }
        }
        flushCartCache(memberId,omsCartItemList);
    }


}
