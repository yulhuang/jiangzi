package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.annotation.RedisCache;
import com.najiujiangzi.jiangzi.dto.ImageDTO;
import com.najiujiangzi.jiangzi.model.Image;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@RedisCache
public interface ImageMapper {

    @Select("<script>" +
            " SELECT * FROM `p_image`" +
            " <where>" +
            " 1 = 1" +
			" <if test=\"dto.id != null\">AND id = #{dto.id}</if>" +
			" <if test=\"dto.user_id != null\">AND user_id = #{dto.user_id}</if>" +
			" <if test=\"dto.image_group_id != null\">AND image_group_id = #{dto.image_group_id}</if>" +
			" <if test=\"dto.image_url != null\">AND image_url = #{dto.image_url}</if>" +
			" <if test=\"dto.describe != null\">AND describe = #{dto.describe}</if>" +
			" <if test=\"dto.create != null\">AND create = #{dto.create}</if>" +
			" <if test=\"dto.update != null\">AND update = #{dto.update}</if>" +
			" <if test=\"dto.type != null\">AND type = #{dto.type}</if>" +
			" <if test=\"dto.numberThumbs != null\">AND number_thumbs = #{dto.numberThumbs}</if>" +
			" <if test=\"dto.numberEnshrine != null\">AND number_enshrine = #{dto.numberEnshrine}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " </where>" +
            "</script>")
    List<Image> find(@Param("dto") ImageDTO dto, @Param("page") Page page);

	@Insert("insert into `p_image`(`user_id`,`image_group_id`,`image_url`,`describe`,`create`,`update`,`type`)  values(#{user_id},#{image_group_id},#{image_url},#{describe},#{create},#{update},#{type})")
	@Options(useGeneratedKeys = true, keyProperty = "series")
	boolean insert(Image model);

    @Update("update `p_image` set user_id=#{user_id},image_group_id=#{image_group_id},image_url=#{image_url},describe=#{describe},create=#{create},update=#{update},type=#{type} where id=#{id}")
	boolean update(ImageDTO dto);

}
