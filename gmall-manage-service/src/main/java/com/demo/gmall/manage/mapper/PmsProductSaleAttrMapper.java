package com.demo.gmall.manage.mapper;

import com.demo.gmall.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/28 13:58
 **/
@org.apache.ibatis.annotations.Mapper
public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId") String skuId);
}
