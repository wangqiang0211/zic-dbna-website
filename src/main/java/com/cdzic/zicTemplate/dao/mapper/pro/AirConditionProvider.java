package com.cdzic.zicTemplate.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Date 2019/04/07 下午 04:29
 * @Author 靳东
 * @ClassName AirConditionProvider
 * @Description
 */
public class AirConditionProvider {
    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.air_condition_type as airConditionType,a.abstracts,a.article_content as articleContent,a.sort,a.update_date as updateDate");
                FROM("air_condition as a");
                if (null!=map.get("headline")){
                    WHERE("a.headline like concat('%',#{headline},'%')");
                }
                if (null!=map.get("articleContent")){
                    WHERE("a.article_content like concat('%',#{articleContent},'%')");
                }
                if (null!=map.get("airConditionType")){
                    WHERE("a.air_condition_type = #{airConditionType}");
                }
                ORDER_BY("update_date desc");
            }
        }.toString();
    }


    public String findByCondition1(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.air_condition_type as airConditionType,a.abstracts,a.article_content as articleContent,a.sort,a.update_date as updateDate");
                FROM("air_condition as a");
                if (null!=map.get("airConditionType")){
                    WHERE("a.air_condition_type = #{airConditionType}");
                }
            }
        }.toString();
    }

    public String findByCondition2(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.air_condition_type as airConditionType,a.abstracts,a.article_content as articleContent,a.sort,a.update_date as updateDate");
                FROM("air_condition as a");
            }
        }.toString();
    }
}
