package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/26 17:31
 **/
@Data
@Table(name = "pms_product_image")
public class PmsProductImage implements Serializable {

    private static final long serialVersionUID = -2827436800751560690L;
    @Id
    @Column
    private String id;
    @Column
    private String productId;
    @Column
    private String imgName;
    @Column
    private String imgUrl;

}
