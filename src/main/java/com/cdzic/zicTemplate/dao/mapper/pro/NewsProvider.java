package com.cdzic.zicTemplate.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
public class NewsProvider {

    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.news_type as newsType,a.abstracts,a.article_content as articleContent,a.sort,a.update_date as updateDate");
                FROM("news as a");
                if (null!=map.get("headline")){
                    WHERE("a.headline like concat('%',#{headline},'%')");
                }
                if (null!=map.get("articleContent")){
                    WHERE("a.article_content like concat('%',#{articleContent},'%')");
                }
                if (null!=map.get("newsType")){
                    WHERE("a.news_type = #{newsType}");
                }
                ORDER_BY("update_date desc");
            }
        }.toString();
    }

    public String findByCondition1(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.news_type as newsType,a.abstracts,a.article_content as articleContent,a.sort,a.update_date as updateDate");
                FROM("news as a");
                if (null!=map.get("newsType")){
                    WHERE("a.news_type = #{newsType}");
                }
            }
        }.toString();
    }
    public String findByCondition2(){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.news_type as newsType,a.abstracts,a.article_content as articleContent,a.sort,a.update_date as updateDate");
                FROM("news as a");
            }
        }.toString();
    }
}
