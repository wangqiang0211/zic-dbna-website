package com.cdzic.zicTemplate.service.impl;

import com.cdzic.zicTemplate.dao.mapper.ProductMapper;
import com.cdzic.zicTemplate.dao.repo.ProductRepo;
import com.cdzic.zicTemplate.domain.Product;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;
import com.cdzic.zicTemplate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2019/04/11 下午 04:32
 * @Author 靳东
 * @ClassName ProductServiceImpl
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product save(String thumbnail, String headline, int productType, String productContent) {
        return productRepo.save(new Product(thumbnail, headline, productType, productContent, 1000, new Date()));
    }

    @Override
    public List<Product> findByCondition(String searchKey, String searchValue,String searchKey2,String searchValue2) {
        String headline = null, articleContent = null;
        Integer productType = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("headline".equals(searchKey)) {
                headline = searchValue;
            } else if ("articleContent".equals(searchKey)) {
                articleContent = searchValue;
            }
        }
        if (searchKey2 != null && !"".equals(searchKey2)){
            if ("productType".equals(searchKey2)){
                if (!"".equals(searchValue2)){
                    productType = Integer.parseInt(searchValue2);
                }
            }
        }

        return productMapper.findByCondition(headline, articleContent,productType);
    }

    @Override
    public int updateProductById(String headline, String thumbnail, int productType, String productContent, Long id) {
        return productRepo.updateProductById(headline, thumbnail, productType, productContent, id);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public PreAndNextEntity getPreEntity(Long id, int productType) {
        PreAndNextEntity pre = new PreAndNextEntity();
        Product product = new Product();
        Long preId = null;
        List<Product> list = new ArrayList<Product>();
        list = productRepo.findProductByProductType(productType);
        int count = list.size();
        long[] strId = new long[count];
        for (int i = 0; i < count; i++) {
            strId[i] = list.get(i).getId();
        }
        for (int j = 0; j < count; j++) {
            if (strId[j] == id) {
                if (j != 0) {
                    preId = strId[j - 1];
                }
            }
        }
        if (null == preId) {
            pre.setId(null);
            pre.setTitle("无");
            pre.setThisType(productType);
        } else {
            product = productRepo.findProductById(preId);
            pre.setId(preId);
            pre.setTitle(product.getHeadline());
            pre.setThisType(product.getProductType());
        }
        return pre;
    }

    @Override
    public PreAndNextEntity getNextEntity(Long id, int productType) {
        PreAndNextEntity next = new PreAndNextEntity();
        Product product = new Product();
        Long nextId = null;
        List<Product> list = new ArrayList<Product>();
        list = productRepo.findProductByProductType(productType);
        int count = list.size();
        long[] strId = new long[count];
        for (int i = 0; i < count; i++) {
            strId[i] = list.get(i).getId();
        }
        for (int j = 0; j < count; j++) {
            if (strId[j] == id) {
                if (j != count - 1){
                    nextId = strId[j + 1];
                }
            }
        }
        if (null == nextId){
            next.setId(null);
            next.setTitle("完");
            next.setThisType(productType);
        }else {
            product = productRepo.findProductById(nextId);
            next.setId(nextId);
            next.setTitle(product.getHeadline());
            next.setThisType(product.getProductType());
        }
        return next;
    }
}
