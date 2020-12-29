package com.kgc.kmall.manger.controller;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.service.AttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-12-16 16:07
 */
@CrossOrigin
@RestController
@Api(tags = "平台属性API",description = "提供平台属性相关的Rest API")
public class AttrController {
    @Reference
    AttrService attrService;
    @ApiOperation(value = "全部平台属性接口",httpMethod = "POST")
    @ApiImplicitParam(name = "catalog3Id",value = "三级分类Id")
    @RequestMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfos(Long catalog3Id) {
        List<PmsBaseAttrInfo> select = attrService.select(catalog3Id);
        return select;
    }
    @ApiOperation(value = "添加平台属性接口",httpMethod = "POST")
    @ApiImplicitParam(name = "attrInfo",value = "平台属性对象")
    @RequestMapping("/saveAttrInfo")
    public Integer saveAttrInfo(@RequestBody PmsBaseAttrInfo attrInfo){
        Integer i = attrService.add(attrInfo);
        return i;
    }
    @ApiOperation(value = "获取平台属性值接口",httpMethod = "POST")
    @ApiImplicitParam(name = "attrId",value = "平台属性Id")
    @RequestMapping("/getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(Long attrId){
        List<PmsBaseAttrValue> attrValueList = attrService.getAttrValueList(attrId);
        return attrValueList;
    }

}
