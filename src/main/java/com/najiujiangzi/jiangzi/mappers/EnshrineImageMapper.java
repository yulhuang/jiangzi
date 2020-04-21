package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.dto.EnshrineImageDTO;
import com.najiujiangzi.jiangzi.model.EnshrineImage;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface EnshrineImageMapper {

    @Select("<script>" +
            " SELECT * FROM `p_enshrine_image`" +
            " <where>" +
            " 1 = 1" +
			" <if test=\"dto.id != null\">AND id = #{dto.id}</if>" +
			" <if test=\"dto.userId != null\">AND user_id = #{dto.userId}</if>" +
			" <if test=\"dto.imageId != null\">AND image_id = #{dto.imageId}</if>" +
			" <if test=\"dto.enshrineGroupId != null\">AND enshrine_group_id = #{dto.enshrineGroupId}</if>" +
			" <if test=\"dto.create != null\">AND create = #{dto.create}</if>" +
			" <if test=\"dto.deleted != null\">AND deleted = #{dto.deleted}</if>" +
			" <if test=\"dto.loseEfficacy != null\">AND lose_efficacy = #{dto.loseEfficacy}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " </where>" +
            "</script>")
    List<EnshrineImage> find(@Param("dto") EnshrineImageDTO dto, @Param("page") Page page);

    @Insert("insert into `p_enshrine_image`(`user_id`,`image_id`,`enshrine_group_id`,`create`,`deleted`,`lose_efficacy`)  values(#{userId},#{imageId},#{enshrineGroupId},#{create},#{deleted},#{loseEfficacy})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(EnshrineImage model);

    @Update("update `p_enshrine_image` set user_id=#{userId},image_id=#{imageId},enshrine_group_id=#{enshrineGroupId},create=#{create},deleted=#{deleted},lose_efficacy=#{loseEfficacy} where id=#{id}")
    int update(EnshrineImageDTO dto);

}
