package com.cdzic.zicTemplate.service.impl;

import com.cdzic.zicTemplate.dao.mapper.VideoShowMapper;
import com.cdzic.zicTemplate.dao.repo.VideoShowRepo;
import com.cdzic.zicTemplate.domain.VideoShow;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;
import com.cdzic.zicTemplate.service.VideoShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Date 2019/04/11 上午 11:34
 * @Author 靳东
 * @ClassName VideoShowServiceImpl
 */
@Service
@Transactional
public class VideoShowServiceImpl implements VideoShowService {

    @Autowired
    private VideoShowRepo videoShowRepo;

    @Autowired
    private VideoShowMapper videoShowMapper;

    @Override
    public VideoShow save(String headline, String thumbnail, String videoShowContent) {
        return videoShowRepo.save(new VideoShow(thumbnail,headline,videoShowContent,1000,new Date()));
    }

    @Override
    public List<VideoShow> findByCondition(String searchKey, String searchValue) {
        String headline = null,videoShowContent = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("headline".equals(searchKey)) {
                headline = searchValue;
            }else if ("videoShowContent".equals(searchKey)) {
                videoShowContent = searchValue;
            }
        }
        return videoShowMapper.findByCondition(headline,videoShowContent);
    }

    @Override
    public void delete(Long id) {
        videoShowRepo.deleteById(id);
    }

    @Override
    public int updateVideoById(String headline, String thumbnail, String videoShowContent, Long id) {
        return videoShowRepo.updateVideoById(headline,thumbnail,videoShowContent,id);
    }

    @Override
    public PreAndNextEntity getPreEntity(Long id) {
        PreAndNextEntity pre = new PreAndNextEntity();
        VideoShow videoShow = new VideoShow();
        Long preId = null;
        List<VideoShow> list = videoShowRepo.findAll();
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
            videoShow = videoShowRepo.findVideoShowById(preId);
            pre.setId(preId);
            pre.setTitle(videoShow.getHeadline());
        }
        return pre;
    }

    @Override
    public PreAndNextEntity getNextEntity(Long id) {
        PreAndNextEntity next = new PreAndNextEntity();
        VideoShow videoShow = new VideoShow();
        Long nextId = null;
        List<VideoShow> list = videoShowRepo.findAll();
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
            videoShow = videoShowRepo.findVideoShowById(nextId);
            next.setId(nextId);
            next.setTitle(videoShow.getHeadline());
        }
        return next;
    }
}
