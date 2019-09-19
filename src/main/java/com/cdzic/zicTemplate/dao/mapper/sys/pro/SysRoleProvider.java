package com.cdzic.zicTemplate.dao.mapper.sys.pro;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @creator yaotao
 * @date 2018/11/17 15:44
 * @describe:
 */
public class SysRoleProvider {
    public String findByCondition(Map<String,String> map){
        return new SQL(){
            {
                SELECT("id,role_name as roleName,description");
                FROM("sys_role");
                if (null!=map.get("roleName")&&!"".equals(map.get("roleName"))){
                    WHERE("role_name like concat('%',#{roleName},'%')");
                }
            }
        }.toString();
    }
}
