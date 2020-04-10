package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository
public interface UserMapper {

    @Select("<script>" +
            " SELECT * FROM sys_user" +
            " <where>" +
            " 1 = 1" +
            " <if test=\"user.id != null\">AND id = #{user.id}</if>" +
            " <if test=\"user.name != null\">AND name = #{user.name}</if>" +
            " <if test=\"user.gender != null\">AND gender = #{user.gender}</if>" +
            " <if test=\"user.email != null\">AND email = #{user.email}</if>" +
            " <if test=\"user.phone != null\">AND phone = #{user.phone}</if>" +
            " <if test=\"user.likeFind != null\">AND name LIKE concat('%',#{user.likeFind},'%')</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            "</where>" +
            "</script>")
    List<User> find(@Param("user") UserDTO dto, @Param("page") Page page);

    @Select("<script>" +
            " SELECT * FROM sys_user" +
            " <where>" +
            " 1 = 1" +
            " <if test=\"id != null\">AND id = #{id}</if>" +
            " <if test=\"name != null\">AND name = #{name}</if>" +
            " <if test=\"gender != null\">AND gender = #{gender}</if>" +
            " <if test=\"email != null\">AND email = #{email}</if>" +
            " <if test=\"phone != null\">AND phone = #{phone}</if>" +
            " <if test=\"likeFind != null\">AND name LIKE concat('%',#{likeFind},'%')</if>" +
            " LIMIT 1" +
            "</where>" +
            "</script>")
    User findOne(UserDTO dto);

}
