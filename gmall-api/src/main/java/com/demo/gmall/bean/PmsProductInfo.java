package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/26 17:31
 **/
@Data
@Table(name = "pms_product_info")
public class PmsProductInfo implements Serializable {

    private static final long serialVersionUID = -3771406133010778496L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String productName;

    @Column
    private String description;

    @Column
    private  String catalog3Id;

    @Transient
    private List<PmsProductSaleAttr> spuSaleAttrList;

    @Transient
    private List<PmsProductImage> spuImageList;
}
