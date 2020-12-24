package com.kgc.kmall.manager.service;

import com.kgc.kmall.bean.*;
import com.kgc.kmall.manager.mapper.*;
import com.kgc.kmall.service.SpuService;
import com.sun.deploy.panel.ITreeNode;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-12-17 14:28
 */
@Component
@Service
public class SpuServiceImpl implements SpuService {
    @Resource
    PmsProductInfoMapper pmsProductInfoMapper;
    @Resource
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Resource
    PmsProductImageMapper pmsProductImageMapper;
    @Resource
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Resource
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(Long catalog3Id) {
        PmsProductInfoExample pmsProductInfoExample=new PmsProductInfoExample();
        PmsProductInfoExample.Criteria criteria = pmsProductInfoExample.createCriteria();
        criteria.andCatalog3IdEqualTo(catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.selectByExample(pmsProductInfoExample);
        return pmsProductInfos;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectByExample(null);
    }

    @Override
    public Integer saveSpuInfo(PmsProductInfo pmsProductInfo) {
        int result=0;
        try {
            pmsProductInfoMapper.insert(pmsProductInfo);
            long pmsProductInfoId=pmsProductInfo.getId();
            List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
            if (spuImageList!=null&&spuImageList.size()>0){
                pmsProductImageMapper.insertForEach(pmsProductInfoId,spuImageList);
            }
            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
                pmsProductSaleAttr.setProductId(pmsProductInfoId);
                int insert = pmsProductSaleAttrMapper.insert(pmsProductSaleAttr);
                if (insert>0){
                    pmsProductSaleAttrValueMapper.insertForEach(pmsProductInfoId,pmsProductSaleAttr.getSpuSaleAttrValueList());
                }
            }
            result=1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(Long spuId) {
        PmsProductSaleAttrExample example=new PmsProductSaleAttrExample();
        example.createCriteria().andProductIdEqualTo(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.selectByExample(example);
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValueExample example1=new PmsProductSaleAttrValueExample();
            example1.createCriteria().andSaleAttrIdEqualTo(pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.selectByExample(example1);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }
        return pmsProductSaleAttrs;
    }

    @Override
    public List<PmsProductImage> spuImageList(Long spuId) {
        PmsProductImageExample pmsProductImageExample=new PmsProductImageExample();
        pmsProductImageExample.createCriteria().andProductIdEqualTo(spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.selectByExample(pmsProductImageExample);
        return pmsProductImages;
    }

}
