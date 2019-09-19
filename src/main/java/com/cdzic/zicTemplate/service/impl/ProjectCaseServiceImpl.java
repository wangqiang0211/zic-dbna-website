package com.cdzic.zicTemplate.service.impl;

import com.cdzic.zicTemplate.dao.mapper.ProjectCaseMapper;
import com.cdzic.zicTemplate.dao.repo.ProjectCaseRepo;
import com.cdzic.zicTemplate.domain.ProjectCase;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;
import com.cdzic.zicTemplate.service.ProjectCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Date 2019/04/11 下午 02:23
 * @Author 靳东
 * @ClassName ProjectCaseServiceImpl
 */
@Service
@Transactional
public class ProjectCaseServiceImpl implements ProjectCaseService {

    @Autowired
    private ProjectCaseRepo projectCaseRepo;
    @Autowired
    private ProjectCaseMapper projectCaseMapper;

    @Override
    public ProjectCase save(String headline, String thumbnail, String projectCaseContent) {
        return projectCaseRepo.save(new ProjectCase(thumbnail, headline, projectCaseContent, 1000, new Date()));
    }

    @Override
    public List<ProjectCase> findByCondition(String searchKey, String searchValue) {
        String headline = null, projectCaseContent = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("headline".equals(searchKey)) {
                headline = searchValue;
            } else if ("projectCaseContent".equals(searchKey)) {
                projectCaseContent = searchValue;
            }
        }
        return projectCaseMapper.findByCondition(headline, projectCaseContent);
    }

    @Override
    public void delete(Long id) {
        projectCaseRepo.deleteById(id);
    }

    @Override
    public int updatePeojectById(String headline, String thumbnail, String projectCaseContent, Long id) {
        return projectCaseRepo.updatePeojectById(headline, thumbnail, projectCaseContent, id);
    }

    @Override
    public PreAndNextEntity getPreEntity(Long id) {
        PreAndNextEntity pre = new PreAndNextEntity();
        ProjectCase projectCase = new ProjectCase();
        Long preId = null;
        List<ProjectCase> list = projectCaseRepo.findAll();
        int count = list.size();
        long[] strId = new long[count];
        for (int i = 0; i < count; i++) {
            strId[i] = list.get(i).getId();
        }
        for (int j = 0; j < count; j++) {
            if (strId[j] == id) {
                if (j != 0) {
                    preId = strId[j - 1];
                }
            }
        }
        if (null == preId) {
            pre.setId(null);
            pre.setTitle("无");
        } else {
            projectCase = projectCaseRepo.findProjectCaseById(preId);
            pre.setId(preId);
            pre.setTitle(projectCase.getHeadline());
        }
        return pre;
    }

    @Override
    public PreAndNextEntity getNextEntity(Long id) {
        PreAndNextEntity next = new PreAndNextEntity();
        ProjectCase projectCase = new ProjectCase();
        Long nextId = null;
        List<ProjectCase> list = projectCaseRepo.findAll();
        int count = list.size();
        long[] strId = new long[count];
        for (int i = 0; i < count; i++) {
            strId[i] = list.get(i).getId();
        }
        for (int j = 0; j < count; j++) {
            if (strId[j] == id) {
                if (j != count - 1) {
                    nextId = strId[j + 1];
                }
            }
        }
        if (null == nextId) {
            next.setId(null);
            next.setTitle("完");
        } else {
            projectCase = projectCaseRepo.findProjectCaseById(nextId);
            next.setId(nextId);
            next.setTitle(projectCase.getHeadline());
        }
        return next;
    }
}
