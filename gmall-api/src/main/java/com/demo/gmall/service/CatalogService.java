package com.demo.gmall.service;

import com.demo.gmall.bean.PmsBaseCatalog1;
import com.demo.gmall.bean.PmsBaseCatalog2;
import com.demo.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/25 20:39
 **/
public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
