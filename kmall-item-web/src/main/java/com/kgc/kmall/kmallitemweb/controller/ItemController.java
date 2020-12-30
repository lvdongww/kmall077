package com.kgc.kmall.kmallitemweb.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.kmall.bean.PmsProductSaleAttr;
import com.kgc.kmall.bean.PmsSkuInfo;
import com.kgc.kmall.bean.PmsSkuSaleAttrValue;
import com.kgc.kmall.service.SkuService;
import com.kgc.kmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-12-29 14:02
 */
@Controller
public class ItemController {
    @Reference
    SkuService skuService;
    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable Long skuId, Model model){
        PmsSkuInfo pmsSkuInfo = skuService.selectBySkuId(skuId);
        model.addAttribute("skuInfo",pmsSkuInfo);
        List<PmsProductSaleAttr> spuSaleAttrListCheckBySku=spuService.spuSaleAttrListIsCheck(pmsSkuInfo.getSpuId(),pmsSkuInfo.getId());
        model.addAttribute("spuSaleAttrListCheckBySku",spuSaleAttrListCheckBySku);
        List<PmsSkuInfo> pmsSkuInfos = skuService.selectBySpuId(pmsSkuInfo.getSpuId());

        Map<String,Long> map=new HashMap<>();
        for (PmsSkuInfo skuInfo : pmsSkuInfos) {
            String k="";
            Long v=skuInfo.getId();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuInfo.getSkuSaleAttrValueList()) {
                k+=pmsSkuSaleAttrValue.getSaleAttrValueId()+"|";
            }
            map.put(k,v);
        }

        String s = JSON.toJSONString(map);
        model.addAttribute("skuSaleAttrHashJsonStr",s);
        return "item";
    }
}
