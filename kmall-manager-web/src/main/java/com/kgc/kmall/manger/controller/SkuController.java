package com.kgc.kmall.manger.controller;

import com.kgc.kmall.bean.PmsSkuInfo;
import com.kgc.kmall.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
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
@Api(tags =  "商品详细属性API",description = "提供商品详细属性相关的Rest API")
public class SkuController {
    @Reference
    SkuService skuService;
    @ApiOperation(value = "添加sku接口",httpMethod = "POST")
    @ApiImplicitParam(name = "skuInfo",value = "sku对象")
    @RequestMapping("/saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo skuInfo){
        String s = skuService.saveSkuInfo(skuInfo);
        return s;
    }
}
