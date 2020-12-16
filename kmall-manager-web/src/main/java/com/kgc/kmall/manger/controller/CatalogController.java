package com.kgc.kmall.manger.controller;

import com.kgc.kmall.bean.PmsBaseCatalog1;
import com.kgc.kmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-12-16 15:20
 */
@Controller
public class CatalogController {
    @Reference
    CatalogService catalogService;
    @RequestMapping("/getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> pmsBaseCatalog1s(){
        return catalogService.getCatalog1();
    }
}
