package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.annotation.RedisCache;
import com.najiujiangzi.jiangzi.dto.RoleDTO;
import com.najiujiangzi.jiangzi.model.Role;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@RedisCache
public interface RoleMapper {

    @Select("<script>" +
            " SELECT * FROM sys_role" +
            " <where>" +
            " 1 = 1" +
            " <if test=\"role.id != null\">AND id = #{role.id}</if>" +
            " <if test=\"role.name != null\">AND name = #{role.name}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " AND deleted = 0" +
            "</where>" +
            "</script>")
    List<Role> find(@Param("role")RoleDTO dto, @Param("page")Page page);

    @Select("SELECT r.* FROM sys_role r LEFT JOIN sys_user_role ur ON r.id = ur.role_id" +
            " LEFT JOIN sys_user u ON ur.user_id = u.id " +
            " WHERE u.id = #{userId} AND u.deleted = 0")
    List<Role> findByUserId(Long userId);

}
