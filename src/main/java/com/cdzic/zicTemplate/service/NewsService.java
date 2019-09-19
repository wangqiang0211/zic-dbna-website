package com.cdzic.zicTemplate.service;

import com.cdzic.zicTemplate.domain.News;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Date 2019/04/03 上午 10:00
 * @Author 靳东
 * @ClassName NewsService
 * @Description
 */
public interface NewsService {

    /**
     * 添加新闻
     * @return
     */
    News save(String thumbnail, String headline,Integer newsType,String abstracts,String articleContent);

    /**
     * 展示新闻
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<News> findByCondition(String searchKey, String searchValue, String searchKey2,String searchValue2);/**

     * 前台展示新闻
     * @param newsType
     * @return
     */
    List<News> findByCondition1(int newsType);


    /**
     * 删除新闻
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 修改新闻
     * @return
     */
    int updateNewsById(String headline,String thumbnail,int newsType,String abstracts, String articleContent,Long id);

    /**
     * 获得上一篇
     * @param id
     * @param newsType
     * @return
     */
    PreAndNextEntity getPreEntity(Long id, int newsType);


    /**
     * 获得下一篇
     * @param id
     * @param newsType
     * @return
     */
    PreAndNextEntity getNextEntity(Long id,int newsType);
}
