package com.cdzic.zicTemplate.dao.mapper;

import com.cdzic.zicTemplate.dao.mapper.pro.ProjectCaseProvider;
import com.cdzic.zicTemplate.domain.ProjectCase;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/04/11 下午 02:25
 * @Author 靳东
 * @ClassName projectCaseMapper
 */
public interface ProjectCaseMapper {

    @SelectProvider(type = ProjectCaseProvider.class, method = "findByCondition")
    List<ProjectCase> findByCondition(@Param("headline") String headline, @Param("projectCaseContent") String projectCaseContent);


    @SelectProvider(type = ProjectCaseProvider.class, method = "findAll")
    List<ProjectCase> findAll();

}
