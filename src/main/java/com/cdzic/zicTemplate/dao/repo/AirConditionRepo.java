package com.cdzic.zicTemplate.dao.repo;

import com.cdzic.zicTemplate.domain.AirCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Date 2019/04/07 下午 04:13
 * @Author 靳东
 * @ClassName AirConditionRepo
 * @Description
 */
public interface AirConditionRepo extends JpaRepository<AirCondition,Long> {

    @Modifying
    @Query("update AirCondition set headline=?1,thumbnail=?2,airConditionType=?3,abstracts=?4,articleContent=?5 where id=?6")
    int  updateAirById(String headline, String thumbnail, int airConditionType,String abstracts, String articleContent, Long id);


    List<AirCondition> findOneById(Long id);

    List<AirCondition> findAirConditionByAirConditionType(int airConditionType);

    AirCondition findAirConditionById(Long id);

//    @Modifying
//    @Query("SELECT * FROM AirCondition WHERE id=(SELECT MAX(id) FROM AirCondition WHERE id<id=?1)")
//    @Query("SELECT * FROM AirCondition WHERE id=(SELECT MAX(id) FROM AirCondition WHERE id<4)")
//    @Query("SELECT * FROM AirCondition WHERE id = (SELECT id FROM AirCondition WHERE id > 4 ORDER BY id asc LIMIT 1)")
//    @Query("select * from AirCondition where id in (select case when SIGN(id-id=?1)>0 THEN MIN(id) when SIGN(id-id=?1)<0 THEN MAX(id) ELSE id end \n" +
//            "from AirCondition where id = 5 GROUP BY SIGN(id-id=?1))")
//    AirCondition findUpById(Long id);






}
