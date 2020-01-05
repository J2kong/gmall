package com.demo.gmall.service;

import com.demo.gmall.bean.PmsSearchParam;
import com.demo.gmall.bean.PmsSearchSkuInfo;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/15 20:58
 **/
public interface SearchService {

    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);

    void  put(String skuId);


    void incrHotScore(String skuId);
}
