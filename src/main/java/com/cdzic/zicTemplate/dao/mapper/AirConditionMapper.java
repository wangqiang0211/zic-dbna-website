package com.cdzic.zicTemplate.dao.mapper;

import com.cdzic.zicTemplate.dao.mapper.pro.AirConditionProvider;
import com.cdzic.zicTemplate.dao.mapper.pro.NewsProvider;
import com.cdzic.zicTemplate.domain.AirCondition;
import com.cdzic.zicTemplate.domain.News;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/04/07 下午 04:22
 * @Author 靳东
 * @ClassName AirConditionMapper
 */
public interface AirConditionMapper {

    @SelectProvider(type = AirConditionProvider.class, method = "findByCondition")
    List<AirCondition> findByCondition(@Param("headline") String headline, @Param("articleContent") String articleContent,@Param("airConditionType") Integer airConditionType);

    @SelectProvider(type = AirConditionProvider.class, method = "findByCondition1")
    List<AirCondition> findByCondition1(@Param("airConditionType") int airConditionType);

    @SelectProvider(type = AirConditionProvider.class, method = "findByCondition2")
    List<AirCondition> findAll();

}
