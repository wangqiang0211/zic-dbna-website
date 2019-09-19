package com.cdzic.zicTemplate.dao.mapper;

import com.cdzic.zicTemplate.dao.mapper.pro.VideoShowProvider;
import com.cdzic.zicTemplate.domain.VideoShow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Date 2019/04/11 上午 11:36
 * @Author 靳东
 * @ClassName VideoShowMapper
 */
public interface VideoShowMapper {

    @SelectProvider(type = VideoShowProvider.class, method = "findByCondition")
    List<VideoShow> findByCondition(@Param("headline") String headline, @Param("videoShowContent") String videoShowContent);

    @SelectProvider(type = VideoShowProvider.class, method = "findAll")
    List<VideoShow> findAll();

}
