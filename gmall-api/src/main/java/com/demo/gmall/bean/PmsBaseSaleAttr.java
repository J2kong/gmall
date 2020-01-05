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
@Table(name = "pms_base_sale_attr")
public class PmsBaseSaleAttr implements Serializable {
    private static final long serialVersionUID = 3321240285153699430L;

    @Id
    @Column
    String id ;

    @Column
    String name;
}
