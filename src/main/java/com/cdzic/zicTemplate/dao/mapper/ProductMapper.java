package com.cdzic.zicTemplate.dao.mapper;

import com.cdzic.zicTemplate.dao.mapper.pro.ProductProvider;
import com.cdzic.zicTemplate.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/04/11 下午 04:08
 * @Author 靳东
 * @ClassName ProductMapper
 */
public interface ProductMapper {

    @SelectProvider(type = ProductProvider.class, method = "findByCondition")
    List<Product> findByCondition(@Param("headline") String headline, @Param("productContent") String productContent,@Param("productType")Integer productType);

    @SelectProvider(type = ProductProvider.class, method = "findByCondition1")
    List<Product> findByCondition1(@Param("productType") Integer productType);

    @SelectProvider(type = ProductProvider.class, method = "findAll")
    List<Product> findAll();


}
