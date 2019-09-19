package com.cdzic.zicTemplate.dao.mapper.sys.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @creator yaotao
 * @date 2018/11/19 10:35
 * @describe:
 */
public class SysUserProvider {

    /**
     * 此项目修改为
     * @param map
     * @return
     */
//    public String findByCondition(Map<String, String> map) {
//        return new SQL() {
//            {
//                SELECT("a.account,a.phone_num as phoneNum,a.email,a.create_date as createDate," +
//                        "a.status");
//                FROM("sys_user as a");
//                if (null != map.get("account")) {
//                    WHERE("a.account like concat('%',#{account},'%')");
//                }
//                if (null != map.get("phoneNum")) {
//                    WHERE("a.phone_num like concat('%',#{phoneNum},'%')");
//                }
//                if (null != map.get("email")) {
//                    WHERE("a.email like concat('%',#{email},'%')");
//                }
//                ORDER_BY("a.create_date asc");
//            }
//        }.toString();
//    }

    public String findByCondition(Map<String, String> map) {
        return new SQL() {
            {
                SELECT("a.account,b.role_name as role,a.phone_num as phoneNum,a.email,a.create_date as createDate," +
                        "a.status");
                FROM("sys_user as a");
                if (null != map.get("account")) {
                    WHERE("a.account like concat('%',#{account},'%')");
                }
                if (null != map.get("phoneNum")) {
                    WHERE("a.phone_num like concat('%',#{phoneNum},'%')");
                }
                if (null != map.get("email")) {
                    WHERE("a.email like concat('%',#{email},'%')");
                }
                LEFT_OUTER_JOIN("sys_role as b on a.role_id = b.id");
                ORDER_BY("a.create_date asc");

            }
        }.toString();

    }
//    public String findByCondition(Map<String, String> map) {
//        return new SQL() {
//            {
//                SELECT("a.account,b.role_name as role,a.phone_num as phoneNum,a.email,a.create_date as createDate," +
//                        "a.status,c.code as seccondLevelCode,c.name as seccondLevelName");
//                FROM("sys_user as a");
//                if (null != map.get("account")) {
//                    WHERE("a.account like concat('%',#{account},'%')");
//                }
//                if (null != map.get("phoneNum")) {
//                    WHERE("a.phone_num like concat('%',#{phoneNum},'%')");
//                }
//                if (null != map.get("email")) {
//                    WHERE("a.email like concat('%',#{email},'%')");
//                }
//                LEFT_OUTER_JOIN("sys_role as b on a.role_id = b.id");
//                LEFT_OUTER_JOIN("seccond_level as c on a.seccond_level_id = c.id");
//                ORDER_BY("a.create_date asc");
//
//            }
//        }.toString();
//    }


    public String findByRoleId(){
        return new SQL(){
            {
                SELECT("account,phone_num as phoneNum");
                FROM("sys_user");
                WHERE("role_id = #{role_id}");
            }
        }.toString();
    }


}
