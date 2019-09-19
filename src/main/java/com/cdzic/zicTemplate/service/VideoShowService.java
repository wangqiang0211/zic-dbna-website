package com.cdzic.zicTemplate.service;

import com.cdzic.zicTemplate.domain.VideoShow;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;

import java.util.List;

/**
 * @Date 2019/04/11 上午 11:31
 * @Author 靳东
 * @ClassName VideoShowService
 */
public interface VideoShowService {

    /**
     * 添加视频
     */
    VideoShow save(String headline,String thumbnail,String videoShowContent);

    /**
     * 展示视频
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<VideoShow> findByCondition(String searchKey,String searchValue);

    /**
     * 根据id删除视频
     */
    void delete(Long id);

    /**
     * 修改视频
     * @param headline
     * @param thumbnail
     * @param videoShowContent
     * @param id
     * @return
     */
    int updateVideoById(String headline,String thumbnail,String videoShowContent,Long id);

    /**
     * 获得上一篇
     *
     * @param id
     * @return
     */
    PreAndNextEntity getPreEntity(Long id);


    /**
     * 获得下一篇
     *
     * @param id
     * @return
     */
    PreAndNextEntity getNextEntity(Long id);

}
