package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.dto.RoleDTO;
import com.najiujiangzi.jiangzi.model.Role;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    @Select("<script>" +
            " SELECT * FROM sys_role" +
            " <where>" +
            " 1 = 1" +
            " <if test=\"role.id != null\">AND id = #{role.id}</if>" +
            " <if test=\"role.name != null\">AND name = #{role.name}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            "</where>" +
            "</script>")
    List<Role> find(@Param("role")RoleDTO dto, @Param("page")Page page);

    @Select("SELECT * FROM sys_role WHERE user_id = #{userId}")
    List<Role> findByUserId(Long userId);


}
