package com.demo.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.gmall.bean.PmsProductInfo;
import com.demo.gmall.manage.utils.PmsUploadUtil;
import com.demo.gmall.service.SpuService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/26 16:30
 **/
@RestController
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("spuList")
    public List<PmsProductInfo> spuList(String catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    @RequestMapping("saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {

        return "";
    }

    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        String imageUrl = PmsUploadUtil.uploadImage(multipartFile);
        return imageUrl;
    }
}