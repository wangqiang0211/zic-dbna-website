package com.cdzic.zicTemplate.dao.mapper.sys.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @creator yaotao
 * @date 2018/11/17 10:34
 * @describe:
 */
public class SysPermissionProvider {
    public String findByCondition(Map<String, String> map) {
        return new SQL() {
            {
                SELECT("id,description,permission");
                FROM("sys_permission");
                if (null != map.get("permission") && !"".equals(map.get("permission"))) {
                    WHERE("permission like concat('%',#{permission},'%')");
                }
            }
        }.toString();
    }
}
