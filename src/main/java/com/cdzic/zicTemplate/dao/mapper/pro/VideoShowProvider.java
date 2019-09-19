package com.cdzic.zicTemplate.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Date 2019/04/11 上午 11:38
 * @Author 靳东
 * @ClassName VideoShowProvider
 */
public class VideoShowProvider {

    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.video_show_content as videoShowContent,a.sort,a.update_date as updateDate");
                FROM("video_show as a");
                if (null!=map.get("headline")){
                    WHERE("a.headline like concat('%',#{headline},'%')");
                }
                if (null!=map.get("videoShowContent")){
                    WHERE("a.video_show_content like concat('%',#{videoShowContent},'%')");
                }
                ORDER_BY("update_date desc");
            }
        }.toString();
    }

    public String findAll(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.video_show_content as videoShowContent,a.sort,a.update_date as updateDate");
                FROM("video_show as a");
            }
        }.toString();
    }
}
