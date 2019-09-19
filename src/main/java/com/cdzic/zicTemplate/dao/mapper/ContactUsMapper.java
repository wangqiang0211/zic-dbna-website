package com.cdzic.zicTemplate.dao.mapper;

import com.cdzic.zicTemplate.dao.mapper.pro.ContactUsProvider;
import com.cdzic.zicTemplate.domain.ContactUs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/04/12 上午 10:16
 * @Author 靳东
 * @ClassName ContactUsMapper
 */
public interface ContactUsMapper {

    @SelectProvider(type = ContactUsProvider.class, method = "findByCondition")
    List<ContactUs> findByCondition(@Param("usKey") String usKey, @Param("usValue") String usValue);


    @SelectProvider(type = ContactUsProvider.class, method = "findByCondition1")
    List<ContactUs> findByCondition1(@Param("usType") int usType);

}
