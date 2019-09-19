package com.cdzic.zicTemplate.service;

import com.cdzic.zicTemplate.domain.AirCondition;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;

import java.util.List;

/**
 * @Date 2019/04/07 下午 04:14
 * @Author 靳东
 * @ClassName AirConditionService
 * @Description
 */
public interface AirConditionService {

    /**
     * 添加空调的新闻
     * @return
     */
    AirCondition save(String thumbnail, String headline, Integer airConditionType,String abstracts, String articleContent);

    /**
     * 展示空调的新闻
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<AirCondition> findByCondition(String searchKey,String searchKey2, String searchValue,String searchValue2);


    /**
     * 删除空调新闻
     * @param id
     */
    void delete(Long id);

    /**
     * 更新空调新闻
     * @param headline
     * @param thumbnail
     * @param airConditionType
     * @param articleContent
     * @param id
     * @return
     */
    int updateNewsById(String headline,String thumbnail,int airConditionType,String abstracts,String articleContent,Long id);

    /**
     * 获得上一篇
     * @param id
     * @param airConditionType
     * @return
     */
    PreAndNextEntity getPreEntity(Long id, int airConditionType);

    /**
     * 获得下一篇
     * @param id
     * @param airConditionType
     * @return
     */
    PreAndNextEntity getNextEntity(Long id,int airConditionType);

}
