package com.cdzic.zicTemplate.dao.repo;

import com.cdzic.zicTemplate.domain.VideoShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Date 2019/04/11 上午 11:30
 * @Author 靳东
 * @ClassName VideoShowRepo
 */
public interface VideoShowRepo extends JpaRepository<VideoShow,Long>{

    @Modifying
    @Query("update VideoShow set headline=?1,thumbnail=?2,videoShowContent=?3 where id=?4")
    int updateVideoById(String headline, String thumbnail, String videoShowContent, Long id);

    List<VideoShow> findOneById(Long id);

    VideoShow findVideoShowById(Long id);
}
