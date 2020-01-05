package com.demo.gmall.service;

import com.demo.gmall.bean.PmsProductInfo;
import com.demo.gmall.bean.PmsSkuInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/28 14:32
 **/
public interface SkuService {
    void saveSkuInfo( PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuByIdFromDb(String skuId);

    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);

    List<PmsSkuInfo> getAllSku(String catalog3Id);

    boolean checkPrice(String productSkuId, BigDecimal price);

    void sendFlushHotScoreQueue(String skuId);
}
