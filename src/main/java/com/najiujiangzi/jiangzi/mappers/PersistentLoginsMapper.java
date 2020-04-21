package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.dto.PersistentLoginsDTO;
import com.najiujiangzi.jiangzi.model.PersistentLogins;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface PersistentLoginsMapper {

    @Select("<script>" +
            " SELECT * FROM `persistent_logins`" +
            " <where>" +
            " 1 = 1" +
			" <if test=\"dto.username != null\">AND username = #{dto.username}</if>" +
			" <if test=\"dto.series != null\">AND series = #{dto.series}</if>" +
			" <if test=\"dto.token != null\">AND token = #{dto.token}</if>" +
			" <if test=\"dto.last_used != null\">AND last_used = #{dto.last_used}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " </where>" +
            "</script>")
    List<PersistentLogins> find(@Param("dto") PersistentLoginsDTO dto, @Param("page") Page page);

    @Insert("insert into `persistent_logins`(`username`,`series`,`token`,`last_used`)  values(#{username},#{series},#{token},#{last_used})")
    @Options(useGeneratedKeys = true, keyProperty = "series")
    int insert(PersistentLogins model);

    @Update("update `persistent_logins` set username=#{username},series=#{series},token=#{token},last_used=#{last_used} where id=#{id}")
    int update(PersistentLoginsDTO dto);

}
