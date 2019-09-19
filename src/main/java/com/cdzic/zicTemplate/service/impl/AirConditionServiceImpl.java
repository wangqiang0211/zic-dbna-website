package com.cdzic.zicTemplate.service.impl;

import com.cdzic.zicTemplate.dao.mapper.AirConditionMapper;
import com.cdzic.zicTemplate.dao.repo.AirConditionRepo;
import com.cdzic.zicTemplate.domain.AirCondition;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;
import com.cdzic.zicTemplate.service.AirConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2019/04/07 下午 04:17
 * @Author 靳东
 * @ClassName AirConditionServiceImpl
 * @Description
 */

@Service
@Transactional
public class AirConditionServiceImpl implements AirConditionService {

    @Autowired
    private AirConditionRepo airConditionRepo;

    @Autowired
    private AirConditionMapper airConditionMapper;

    @Override
    public AirCondition save(String thumbnail, String headline, Integer airConditionType, String abstracts,String articleContent) {
        return airConditionRepo.save(new AirCondition(thumbnail,headline,airConditionType,abstracts,articleContent,1000,new Date()));
    }

    @Override
    public List<AirCondition> findByCondition(String searchKey,String searchKey2, String searchValue,String searchValue2) {
        String headline = null,articleContent = null;
        Integer airConditionType=null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("headline".equals(searchKey)) {
                headline = searchValue;
            }else if ("articleContent".equals(searchKey)) {
                articleContent = searchValue;
            }
        }
        if (null != searchKey2 && !"".equals(searchKey2)){
            if ("airConditionType".equals(searchKey2)) {
                if (!"".equals(searchValue2)) {
                    airConditionType = Integer.parseInt(searchValue2);
                }
            }
        }
        return airConditionMapper.findByCondition(headline,articleContent,airConditionType);
    }

    @Override
    public void delete(Long id) {
        airConditionRepo.deleteById(id);
    }

    @Override
    public int updateNewsById(String headline, String thumbnail, int airConditionType,String abstracts, String articleContent, Long id) {
        return airConditionRepo.updateAirById(headline,thumbnail,airConditionType,abstracts,articleContent,id);
    }

    @Override
    public PreAndNextEntity getPreEntity(Long id, int airConditionType) {
        PreAndNextEntity pre = new PreAndNextEntity();
        AirCondition airCondition = new AirCondition();
        Long preId = null;
        List<AirCondition> list = new ArrayList<AirCondition>();
        list = airConditionRepo.findAirConditionByAirConditionType(airConditionType);
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
            pre.setThisType(airConditionType);
        } else {
            airCondition = airConditionRepo.findAirConditionById(preId);
            pre.setId(preId);
            pre.setTitle(airCondition.getHeadline());
            pre.setThisType(airCondition.getAirConditionType());
        }
        return pre;
    }

    @Override
    public PreAndNextEntity getNextEntity(Long id, int airConditionType) {
        PreAndNextEntity next = new PreAndNextEntity();
        AirCondition airCondition = new AirCondition();
        Long nextId = null;
        List<AirCondition> list = new ArrayList<AirCondition>();
        list = airConditionRepo.findAirConditionByAirConditionType(airConditionType);
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
        if (null == nextId) {
            next.setId(null);
            next.setTitle("完");
            next.setThisType(airConditionType);
        } else {
            airCondition = airConditionRepo.findAirConditionById(nextId);
            next.setId(nextId);
            next.setTitle(airCondition.getHeadline());
            next.setThisType(airCondition.getAirConditionType());
        }
        return next;
    }
}
