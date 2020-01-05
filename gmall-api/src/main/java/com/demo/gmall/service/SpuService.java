package com.demo.gmall.service;

import com.demo.gmall.bean.PmsProductImage;
import com.demo.gmall.bean.PmsProductInfo;
import com.demo.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/26 17:38
 **/
public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId, String skuId);
}
