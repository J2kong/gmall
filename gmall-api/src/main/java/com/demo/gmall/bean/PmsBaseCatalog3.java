package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/25 20:28
 **/
@Data
@Table(name = "pms_base_catalog3")
public class PmsBaseCatalog3 implements Serializable {
    private static final long serialVersionUID = -1451689277237177426L;

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String catalog2Id;
}
