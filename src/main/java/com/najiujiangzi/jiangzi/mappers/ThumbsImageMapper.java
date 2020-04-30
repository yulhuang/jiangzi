package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.annotation.RedisCache;
import com.najiujiangzi.jiangzi.dto.ThumbsImageDTO;
import com.najiujiangzi.jiangzi.model.ThumbsImage;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@RedisCache
public interface ThumbsImageMapper {

    @Select("<script>" +
            " SELECT * FROM `p_thumbs_image`" +
            " <where>" +
            " 1 = 1" +
			" <if test=\"dto.id != null\">AND id = #{dto.id}</if>" +
			" <if test=\"dto.userId != null\">AND user_id = #{dto.userId}</if>" +
			" <if test=\"dto.imageId != null\">AND image_id = #{dto.imageId}</if>" +
			" <if test=\"dto.create != null\">AND create = #{dto.create}</if>" +
			" <if test=\"dto.deleted != null\">AND deleted = #{dto.deleted}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " </where>" +
            "</script>")
    List<ThumbsImage> find(@Param("dto") ThumbsImageDTO dto, @Param("page") Page page);

    @Insert("insert into `p_thumbs_image`(`user_id`,`image_id`,`create`,`deleted`)  values(#{userId},#{imageId},#{create},#{deleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean insert(ThumbsImage model);

    @Update("update `p_thumbs_image` set user_id=#{userId},image_id=#{imageId},create=#{create},deleted=#{deleted} where id=#{id}")
    boolean update(ThumbsImageDTO dto);

}
