package com.demo.gmall.service;

import com.demo.gmall.bean.PmsBaseAttrInfo;
import com.demo.gmall.bean.PmsBaseAttrValue;
import com.demo.gmall.bean.PmsBaseSaleAttr;

import java.util.List;
import java.util.Set;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/25 21:49
 **/
public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
