package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/25 21:50
 **/
@Data
@Table(name = "pms_base_attr_info")
public class PmsBaseAttrInfo  implements Serializable {
    private static final long serialVersionUID = -8531476552864631472L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;
    @Column
    private String isEnabled;

    @Transient
    List<PmsBaseAttrValue> attrValueList;

}
