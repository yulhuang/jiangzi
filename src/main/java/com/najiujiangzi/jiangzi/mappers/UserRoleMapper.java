package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.annotation.RedisCache;
import com.najiujiangzi.jiangzi.dto.UserRoleDTO;
import com.najiujiangzi.jiangzi.model.UserRole;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@RedisCache
public interface UserRoleMapper {

    @Select("<script>" +
            " SELECT * FROM sys_user_role" +
            " <where>" +
            " 1 = 1" +
            " <if test=\"dto.id != null\">AND id = #{dto.id}</if>" +
            " <if test=\"dto.userId != null\">AND user_id = #{dto.userId}</if>" +
            " <if test=\"dto.roleId != null\">AND role_id = #{dto.roleId}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            "</where>" +
            "</script>")
    List<UserRole> find(@Param("dto")UserRoleDTO dto, @Param("page")Page page);

    @Insert("INSERT INTO sys_user_role(`user_id`, `role_id`) values(#{userId}, (select id from sys_role where name = 'COMMON'))")
    boolean insertCommonUser(Long userId);
}
