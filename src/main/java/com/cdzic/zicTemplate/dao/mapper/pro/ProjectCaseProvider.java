package com.cdzic.zicTemplate.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Date 2019/04/11 下午 02:29
 * @Author 靳东
 * @ClassName ProjectCaseProvider
 */
public class ProjectCaseProvider {
    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("a.id,a.thumbnail,a.headline,a.project_case_content as projectCaseContent,a.sort,a.update_date as updateDate");
                FROM("project_case as a");
                if (null!=map.get("headline")){
                    WHERE("a.headline like concat('%',#{headline},'%')");
                }
                if (null!=map.get("projectCaseContent")){
                    WHERE("a.project_case_content like concat('%',#{projectCaseContent},'%')");
                }
                ORDER_BY("update_date desc");
            }
        }.toString();
    }


    public String findAll(Map<String, String> map) {
        return new SQL() {
            {
                SELECT("a.id,a.thumbnail,a.headline,a.project_case_content as projectCaseContent,a.sort,a.update_date as updateDate");
                FROM("project_case as a");
            }

        }.toString();
    }
}
