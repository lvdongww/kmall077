package com.kgc.kmall.manger.controller;

import com.kgc.kmall.bean.PmsBaseCatalog1;
import com.kgc.kmall.bean.PmsBaseCatalog2;
import com.kgc.kmall.bean.PmsBaseCatalog3;
import com.kgc.kmall.service.CatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-12-16 15:20
 */
@CrossOrigin
@RestController
@Api(tags = "平台分类API",description = "提供平台分类相关的Rest API")
public class CatalogController {
    @Reference
    CatalogService catalogService;
    @ApiOperation(value = "获取一级分类",httpMethod = "POST")
    @RequestMapping("/getCatalog1")
    public List<PmsBaseCatalog1> pmsBaseCatalog1s(){
        return catalogService.getCatalog1();
    }
    @ApiOperation(value = "获取二级分类",httpMethod = "POST")
    @ApiImplicitParam(name = "catalog1Id",value = "一级分类Id")
    @RequestMapping("/getCatalog2")
    public List<PmsBaseCatalog2> pmsBaseCatalog2s(Integer catalog1Id){
        return catalogService.getCatalog2(catalog1Id);
    }
    @ApiOperation(value = "获取三级分类",httpMethod = "POST")
    @ApiImplicitParam(name = "catalog2Id",value = "二级分类Id")
    @RequestMapping("/getCatalog3")
    public List<PmsBaseCatalog3> pmsBaseCatalog3s(Integer catalog2Id){
        return catalogService.getCatalog3(catalog2Id);
    }
}
