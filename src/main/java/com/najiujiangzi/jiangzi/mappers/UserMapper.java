package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from sys_user")
    List<UserDTO> findAll();

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
            "</where>" +
            "</script>")
    List<User> find(UserDTO dto);

}
