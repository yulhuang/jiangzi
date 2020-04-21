package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.dto.VerificationDTO;
import com.najiujiangzi.jiangzi.model.Verification;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VerificationMapper {

    @Select("<script>" +
            " SELECT * FROM `sys_verification`" +
            " <where>" +
            " 1 = 1" +
			" <if test=\"dto.id != null\">AND id = #{dto.id}</if>" +
			" <if test=\"dto.code != null\">AND code = #{dto.code}</if>" +
			" <if test=\"dto.email != null\">AND email = #{dto.email}</if>" +
			" <if test=\"dto.create != null\">AND create = #{dto.create}</if>" +
            "<if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            "</where>" +
            "</script>")
    List<Verification> find(@Param("dto") VerificationDTO dto, @Param("page") Page page);

    @Insert("insert into `sys_verification`(`code`, `email`, `create`)  values(#{code},#{email},#{create})")
    int insert(Verification model);

    @Update("update `sys_verification` set code=#{code},email=#{email},create=#{create} where id=#{id}")
    int update(VerificationDTO dto);

    @Select("SELECT * FROM sys_verification WHERE code = #{code} AND email = #{email} AND `create` > (NOW() - INTERVAL 5 MINUTE)")
    VerificationDTO findByCodeAndEmail(String code, String email);

    @Delete("delete from sys_verification where id = #{id}")
    int deleteById(Long id);
}
