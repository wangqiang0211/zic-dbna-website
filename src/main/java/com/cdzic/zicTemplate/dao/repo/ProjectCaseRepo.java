package com.cdzic.zicTemplate.dao.repo;

import com.cdzic.zicTemplate.domain.ProjectCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Date 2019/04/11 下午 02:20
 * @Author 靳东
 * @ClassName ProjectCaseRepo
 */
public interface ProjectCaseRepo extends JpaRepository<ProjectCase,Long>{

    @Modifying
    @Query("update ProjectCase set headline=?1,thumbnail=?2,projectCaseContent=?3 where id=?4")
    int updatePeojectById(String headline, String thumbnail, String projectCaseContent, Long id);

    List<ProjectCase> findOneById(Long id);

    ProjectCase findProjectCaseById(Long id);
}
