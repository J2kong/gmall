package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/25 20:28
 **/
@Data
@Table(name = "pms_base_catalog2")
public class PmsBaseCatalog2 implements Serializable {
    private static final long serialVersionUID = -8044638865628544700L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String catalog1Id;

    @Transient
    private List<PmsBaseCatalog3> catalog3List;
}
