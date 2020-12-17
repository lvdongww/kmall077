package com.kgc.kmall.manger.controller;

import com.kgc.kmall.bean.PmsBaseSaleAttr;
import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-12-17 14:56
 */
@CrossOrigin
@RestController
public class SpuController {
    @Reference
    SpuService spuService;
    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(Long catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }


    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> saleAttrList = spuService.baseSaleAttrList();
        return saleAttrList;
    }
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file){
        return "12312312";
    }
    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        return "success";
    }
}
