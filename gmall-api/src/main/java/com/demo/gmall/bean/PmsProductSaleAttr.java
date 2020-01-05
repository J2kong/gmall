package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/26 17:31
 **/
@Data
@Table(name = "pms_product_sale_attr")
public class PmsProductSaleAttr implements Serializable {
    private static final long serialVersionUID = -3393695762548323295L;
    @Id
    @Column
    String id ;

    @Column
    String productId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrName;


    @Transient
    List<PmsProductSaleAttrValue> spuSaleAttrValueList;
}
