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
@Table(name = "pms_base_catalog1")
public class PmsBaseCatalog1 implements Serializable {
    private static final long serialVersionUID = 7082472777650494312L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Transient
    private List<PmsBaseCatalog2> catalog2s;

}
