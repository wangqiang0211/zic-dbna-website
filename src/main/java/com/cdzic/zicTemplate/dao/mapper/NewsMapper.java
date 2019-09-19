package com.cdzic.zicTemplate.dao.mapper;

import com.cdzic.zicTemplate.dao.mapper.pro.NewsProvider;
import com.cdzic.zicTemplate.domain.News;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/04/03 上午 10:09
 * @Author 靳东
 * @ClassName NewsMapper
 * @Description TODO
 */
public interface NewsMapper {

    @SelectProvider(type = NewsProvider.class, method = "findByCondition")
    List<News> findByCondition(@Param("headline") String headline, @Param("articleContent") String articleContent, @Param("newsType") Integer newsType);

    @SelectProvider(type = NewsProvider.class, method = "findByCondition1")
    List<News> findByCondition1(@Param("newsType") int newsType);

    @SelectProvider(type = NewsProvider.class, method = "findByCondition2")
    List<News> findAll();
}
