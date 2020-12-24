package com.kgc.kmall.manger.controller;

import com.kgc.kmall.bean.PmsSkuInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shkstart
 * @create 2020-12-23 19:08
 */
@CrossOrigin
@RestController
public class SkuController {
    @RequestMapping("/saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo skuInfo){
        return "success";
    }
}
