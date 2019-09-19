package com.cdzic.zicTemplate.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Date 2019/04/12 上午 10:19
 * @Author 靳东
 * @ClassName ContactUsProvider
 */
public class ContactUsProvider {
    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.us_key as usKey,a.us_value as usValue,a.us_type as usType,a.sort,a.create_date as createDate");
                FROM("contact_us as a");
                if (null!=map.get("usKey")){
                    WHERE("a.us_key like concat('%',#{usKey},'%')");
                }
                if (null!=map.get("usValue")){
                    WHERE("a.us_value like concat('%',#{usValue},'%')");
                }
                ORDER_BY("sort");
            }
        }.toString();
    }
    public String findByCondition1(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.us_key as usKey,a.us_value as usValue,a.us_type as usType,a.sort,a.create_date as createDate");
                FROM("contact_us as a");
                if (null!=map.get("usType")){
                    WHERE("a.us_type = #{usType}");
                }
                ORDER_BY("sort asc");
            }
        }.toString();
    }

}
