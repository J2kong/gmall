package com.demo.gmall.search.utils;

import com.demo.gmall.bean.PmsSearchParam;
import org.apache.commons.lang3.StringUtils;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/19 14:28
 **/
public class urlutil {


  public static String getUrlParam(PmsSearchParam pmsSearchParam, String... delValueId) {
        String catalog3Id = pmsSearchParam.getCatalog3Id();
        String keyword = pmsSearchParam.getKeyword();
        String[] skuAttrValueList = pmsSearchParam.getValueId();
        String urlParam = null;

        if (StringUtils.isNotBlank(keyword)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "keyword=" + keyword;
        }

        if (StringUtils.isNotBlank(catalog3Id)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "catalog3Id=" + catalog3Id;
        }

        if (skuAttrValueList != null) {
            for (String skuAttrValue : skuAttrValueList) {
                if (!skuAttrValue.equals(delValueId)) {
                    urlParam = urlParam + "&valueId=" + skuAttrValue;
                }
            }
        }
        return urlParam;
    }


}
