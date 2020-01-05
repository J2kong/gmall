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
 * @date2019/9/25 21:52
 **/
@Data
@Table(name = "pms_base_attr_value")
public class PmsBaseAttrValue implements Serializable {

    private static final long serialVersionUID = 1499999363212502266L;
    @Id
    @Column
    private String id;
    @Column
    private String valueName;
    @Column
    private String attrId;
    @Column
    private String isEnabled;
    @Transient
    private String urlParam;

}
