package com.cdzic.zicTemplate.service;

import com.cdzic.zicTemplate.domain.Product;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;

import java.util.List;

/**
 * @Date 2019/04/11 下午 04:28
 * @Author 靳东
 * @ClassName ProductService
 */
public interface ProductService {

    /**
     * 添加产品
     * @param thumbnail
     * @param headline
     * @param productType
     * @param productContent
     * @return
     */
    Product save(String thumbnail,String headline,int productType,String productContent);


    /**
     * 展示商品
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<Product> findByCondition(String searchKey, String searchValue,String searchKey2,String searchValue2);


    /**
     * 修改产品
     * @param headline
     * @param thumbnail
     * @param productType
     * @param productContent
     * @param id
     * @return
     */
    int updateProductById(String headline,String thumbnail,int productType,String productContent,Long id);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Long id);

    /**
     * 获得上一篇
     * @param id
     * @param productType
     * @return
     */
    PreAndNextEntity getPreEntity(Long id,int productType);


    /**
     * 获得下一篇
     * @param id
     * @param productType
     * @return
     */
    PreAndNextEntity getNextEntity(Long id,int productType);


}
