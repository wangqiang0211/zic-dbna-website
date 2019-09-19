package com.cdzic.zicTemplate.service.impl;

import com.cdzic.zicTemplate.dao.mapper.NewsMapper;
import com.cdzic.zicTemplate.dao.repo.NewsRepo;
import com.cdzic.zicTemplate.domain.News;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;
import com.cdzic.zicTemplate.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2019/04/03 上午 10:03
 * @Author 靳东
 * @ClassName NewsServiceImpl
 * @Description TODO
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public News save(String thumbnail, String headline,Integer newsType,String abstracts, String articleContent) {
        return newsRepo.save(new News(thumbnail,headline,newsType,abstracts,articleContent,1,new Date()));
    }

    @Override
    public List<News> findByCondition(String searchKey, String searchValue,String searchKey2,String searchValue2) {
        String headline = null,articleContent = null;
        Integer newsType = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("headline".equals(searchKey)) {
                headline = searchValue;
            } else if ("articleContent".equals(searchKey)) {
                articleContent = searchValue;
            }
        }
        if (searchKey2 != null && !"".equals(searchKey2)){
            if ("newsType".equals(searchKey2)){
                if (!"".equals(searchValue2)){
                    newsType = Integer.parseInt(searchValue2);
                }
            }
        }

        return newsMapper.findByCondition(headline, articleContent,newsType);
    }

    @Override
    public List<News> findByCondition1(int newsType) {
        return newsMapper.findByCondition1(newsType);
    }

    @Override
    public void delete(Long id) {
        newsRepo.deleteById(id);
    }

    @Override
    public int updateNewsById(String headline, String thumbnail, int newsType,String abstracts, String articleContent, Long id) {
        return newsRepo.updateNewsById(headline,thumbnail,newsType,abstracts,articleContent,id);
    }

    @Override
    public PreAndNextEntity getPreEntity(Long id, int newsType) {
        PreAndNextEntity pre = new PreAndNextEntity();
        News news = new News();
        Long preId = null;
        List<News> list = new ArrayList<News>();
        list = newsRepo.findNewsByNewsType(newsType);
        int count = list.size();
        //存id
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
            pre.setThisType(newsType);
        } else {
            news = newsRepo.findNewsById(preId);
            pre.setId(preId);
            pre.setTitle(news.getHeadline());
            pre.setThisType(news.getNewsType());
        }
        return pre;
    }

    @Override
    public PreAndNextEntity getNextEntity(Long id, int newsType) {
        PreAndNextEntity next = new PreAndNextEntity();
        News news = new News();
        Long nextId = null;
        List<News> list = new ArrayList<News>();
        list = newsRepo.findNewsByNewsType(newsType);
        int count = list.size();
        long[] strId = new long[count];
        for (int i = 0; i < count; i++) {
            strId[i] = list.get(i).getId();
        }
        for (int j = 0; j < count; j++) {
            if (strId[j] == id) {
                if (j != count - 1){
                    nextId = strId[j + 1];
                }
            }
        }
        if (null == nextId){
            next.setId(null);
            next.setTitle("完");
            next.setThisType(newsType);
        }else {
            news = newsRepo.findNewsById(nextId);
            next.setId(nextId);
            next.setTitle(news.getHeadline());
            next.setThisType(news.getNewsType());
        }
        return next;
    }

}
