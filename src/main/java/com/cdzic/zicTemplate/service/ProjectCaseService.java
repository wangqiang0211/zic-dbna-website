package com.cdzic.zicTemplate.service;

import com.cdzic.zicTemplate.domain.ProjectCase;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;

import java.util.List;

/**
 * @Date 2019/04/11 下午 02:21
 * @Author 靳东
 * @ClassName ProjectCaseService
 */
public interface ProjectCaseService {

    ProjectCase save(String headline, String thumbnail, String projectCaseContent);

    /**
     * 案例列表
     *
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<ProjectCase> findByCondition(String searchKey, String searchValue);

    /**
     * 删除案例
     *
     * @param id
     */
    void delete(Long id);

    int updatePeojectById(String headline, String thumbnail, String projectCaseContent, Long id);

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
