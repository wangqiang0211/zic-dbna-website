package com.cdzic.zicTemplate.dao.repo;

import com.cdzic.zicTemplate.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Date 2019/04/03 上午 09:58
 * @Author 靳东
 * @ClassName NewsRepo
 * @Description
 */
public interface NewsRepo extends JpaRepository<News,Long>{

    @Modifying
    @Query("update News set headline=?1,thumbnail=?2,newsType=?3,abstracts=?4,articleContent=?5 where id=?6")
    int updateNewsById(String headline, String thumbnail, int newsType,String abstracts, String articleContent, Long id);

    List<News> findOneById(Long id);

    List<News> findNewsByNewsType(int newsType);

    News findNewsById(Long id);
}
