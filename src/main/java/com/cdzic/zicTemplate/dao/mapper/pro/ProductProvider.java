package com.cdzic.zicTemplate.dao.mapper.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Date 2019/04/11 下午 04:15
 * @Author 靳东
 * @ClassName ProductProvider
 */
public class ProductProvider {
    public String findByCondition(Map<String, String> map) {
        return new SQL() {
            {
                SELECT("a.id,a.thumbnail,a.headline,a.product_type as productType,a.product_content as productContent,a.sort,a.update_date as updateDate");
                FROM("product as a");
                if (null != map.get("headline")) {
                    WHERE("a.headline like concat('%',#{headline},'%')");
                }
                if (null != map.get("productContent")) {
                    WHERE("a.product_content like concat('%',#{productContent},'%')");
                }
                if (null != map.get("productType")) {
                    WHERE("a.product_type = #{productType}");
                }
                ORDER_BY("update_date desc");
            }

        }.toString();
    }

    public String findByCondition1(Map<String, String> map) {
        return new SQL() {
            {
                SELECT("a.id,a.thumbnail,a.headline,a.product_type as productType,a.product_content as productContent,a.sort,a.update_date as updateDate");
                FROM("product as a");
                if (null!=map.get("productType")){
                    WHERE("a.product_type = #{productType}");
                }
            }

        }.toString();
    }

    public String findAll(Map<String, String> map) {
        return new SQL() {
            {
                SELECT("a.id,a.thumbnail,a.headline,a.product_type as productType,a.product_content as productContent,a.sort,a.update_date as updateDate");
                FROM("product as a");
            }

        }.toString();
    }


}
