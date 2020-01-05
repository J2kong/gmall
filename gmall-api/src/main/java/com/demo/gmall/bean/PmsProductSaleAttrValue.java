package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/26 17:32
 **/
@Data
@Table(name = "pms_product_sale_attr_value")
public class PmsProductSaleAttrValue implements Serializable {
    private static final long serialVersionUID = 5974883473478293244L;
    @Id
    @Column
    String id ;

    @Column
    String productId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrValueName;

    @Transient
    String isChecked;
}
