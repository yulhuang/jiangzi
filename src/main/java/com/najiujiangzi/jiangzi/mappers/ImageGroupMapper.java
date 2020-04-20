package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.dto.ImageGroupDTO;
import com.najiujiangzi.jiangzi.model.ImageGroup;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ImageGroupMapper {

    @Select("<script>" +
            " SELECT * FROM `p_image_group`" +
            " <where>" +
            " 1 = 1" +
			" <if test=\"dto.id != null\">AND id = #{dto.id}</if>" +
			" <if test=\"dto.name != null\">AND name = #{dto.name}</if>" +
			" <if test=\"dto.describe != null\">AND describe = #{dto.describe}</if>" +
			" <if test=\"dto.type != null\">AND type = #{dto.type}</if>" +
			" <if test=\"dto.image_count != null\">AND image_count = #{dto.image_count}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " </where>" +
            "</script>")
    List<ImageGroup> find(@Param("dto") ImageGroupDTO dto, @Param("page") Page page);

    @Insert("insert into `p_image_group`(`name`,`describe`,`type`,`image_count`)  values(#{name},#{describe},#{type},#{image_count})")
    int insert(ImageGroup model);

    @Update("update `p_image_group` set name=#{name},describe=#{describe},type=#{type},image_count=#{image_count} where id=#{id}")
    int update(ImageGroupDTO dto);

}
