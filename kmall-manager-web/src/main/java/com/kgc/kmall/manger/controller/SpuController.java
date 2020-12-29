package com.kgc.kmall.manger.controller;

import com.kgc.kmall.bean.PmsBaseSaleAttr;
import com.kgc.kmall.bean.PmsProductImage;
import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.bean.PmsProductSaleAttr;
import com.kgc.kmall.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-12-17 14:56
 */
@CrossOrigin
@RestController
@Api(tags = "商品系列属性API",description = "提供商品系列属性相关的Rest API")
public class SpuController {
    @Reference
    SpuService spuService;
    @Value("${file.server}")
    String fileUrl;

    @ApiOperation(value = "3级分类查询SPU",httpMethod = "POST")
    @ApiImplicitParam(name = "catalog3Id",value = "3级分类Id")
    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(Long catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    @ApiOperation(value = "获取销售属性",httpMethod = "POST")
    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> saleAttrList = spuService.baseSaleAttrList();
        return saleAttrList;
    }

    @ApiOperation(value = "添加spu照片",httpMethod = "POST")
    @ApiImplicitParam(name = "file",value = "文件")
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file) throws IOException, MyException {
        //文件上传
        //返回文件上传后的路径
        String imgUrl="http://"+fileUrl;
        if(file!=null){
            System.out.println("multipartFile = " + file.getName()+"|"+file.getSize());
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            String filename=    file.getOriginalFilename();
            String extName = FilenameUtils.getExtension(filename);
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            imgUrl=fileUrl ;
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                imgUrl+="/"+path;
            }
        }
        System.out.println(imgUrl);
        return imgUrl;
    }
    @ApiOperation(value = "添加spu信息",httpMethod = "POST")
    @ApiImplicitParam(name = "pmsProductInfo",value = "spu对象")
    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }
    @ApiOperation(value = "添加sku查询销售属性",httpMethod = "POST")
    @ApiImplicitParam(name = "spuId",value = "spuId")
    @RequestMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(Long spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrList=spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrList;
    }
    @ApiOperation(value = "添加sku查询spu全部图片",httpMethod = "POST")
    @ApiImplicitParam(name = "spuId",value = "spuId")
    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(Long spuId){
        List<PmsProductImage> pmsProductImageList = spuService.spuImageList(spuId);
        return pmsProductImageList;
    }
}
