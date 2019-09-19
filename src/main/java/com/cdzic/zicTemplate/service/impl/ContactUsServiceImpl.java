package com.cdzic.zicTemplate.service.impl;

import com.cdzic.zicTemplate.dao.mapper.ContactUsMapper;
import com.cdzic.zicTemplate.dao.repo.ContactUsRepo;
import com.cdzic.zicTemplate.domain.ContactUs;
import com.cdzic.zicTemplate.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Date 2019/04/12 上午 10:03
 * @Author 靳东
 * @ClassName ContactUsServiceImpl
 */
@Service
@Transactional
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepo contactUsRepo;
    @Autowired
    private ContactUsMapper contactUsMapper;

    @Override
    public ContactUs save(String usKey, String usValue, int usType,int sort) {
        return contactUsRepo.save(new ContactUs(usKey,usValue,usType,sort,new Date()));
    }

    @Override
    public List<ContactUs> findByCondition(String searchKey, String searchValue) {
        String usKey = null,usValue = null;
        if (null != searchKey && !"".equals(searchKey)) {
            if ("usKey".equals(searchKey)) {
                usKey = searchValue;
            }else if ("usValue".equals(searchKey)) {
                usValue = searchValue;
            }
        }
        return contactUsMapper.findByCondition(usKey,usValue);
    }

    @Override
    public void delete(Long id) {
        contactUsRepo.deleteById(id);
    }

    @Override
    public int updateContactUsById(String usKey, String usValue, int usType,int sort,Long id) {
        return contactUsRepo.updateContactUsById(usKey,usValue,usType,sort,id);
    }

    @Override
    public int updateContactSortById(Integer sort,Long id) {
        return contactUsRepo.updateContactSortById(sort,id);
    }
}
