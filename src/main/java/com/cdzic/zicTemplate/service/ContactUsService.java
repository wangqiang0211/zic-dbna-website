package com.cdzic.zicTemplate.service;

import com.cdzic.zicTemplate.domain.ContactUs;

import java.util.List;

/**
 * @Date 2019/04/12 上午 09:17
 * @Author 靳东
 * @ClassName ContactUsService
 */
public interface ContactUsService {

    /**
     * 保存
     * @param usKey
     * @param usValue
     * @param usType
     * @return
     */
    ContactUs save(String usKey,String usValue,int usType,int sort);

    /**
     * 展示信息
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<ContactUs> findByCondition(String searchKey,String searchValue);

    /**
     * 删除一条信息
     * @param id
     */
    void delete(Long id);

    /**
     * 修改信息
     * @param usKey
     * @param usValue
     * @param usType
     * @return
     */
    int updateContactUsById(String usKey,String usValue,int usType,int sort,Long id);

    int updateContactSortById(Integer sort,Long id);
}
