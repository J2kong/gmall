package com.demo.gmall.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/15 20:59
 **/
@Data
public class PmsSearchParam implements Serializable {
    private static final long serialVersionUID = -8462221436037593522L;
    private String catalog3Id;

    private String keyword;

    private String[] valueId;

}
