package com.cdzic.zicTemplate.dao.repo;

import com.cdzic.zicTemplate.domain.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Date 2019/04/12 上午 09:15
 * @Author 靳东
 * @ClassName ContactUsRepo
 */
public interface ContactUsRepo extends JpaRepository<ContactUs,Long> {

    @Modifying
    @Query("update ContactUs set usKey=?1,usValue=?2,usType=?3,sort=?4 where id=?5")
    int updateContactUsById(String usKey, String usValue, int usType,int sort,Long id);


    @Modifying
    @Query("update ContactUs set sort=?1 where id=?2")
    int updateContactSortById(Integer sort,Long id);

    ContactUs findBySort(Integer sort);
}
