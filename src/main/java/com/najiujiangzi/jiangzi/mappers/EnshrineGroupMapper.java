package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.annotation.RedisCache;
import com.najiujiangzi.jiangzi.dto.EnshrineGroupDTO;
import com.najiujiangzi.jiangzi.model.EnshrineGroup;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@RedisCache
public interface EnshrineGroupMapper {

    @Select("<script>" +
            " SELECT * FROM `e_enshrine_group`" +
            " <where>" +
            " 1 = 1" +
			" <if test=\"dto.id != null\">AND id = #{dto.id}</if>" +
			" <if test=\"dto.userId != null\">AND user_id = #{dto.userId}</if>" +
			" <if test=\"dto.name != null\">AND name = #{dto.name}</if>" +
			" <if test=\"dto.number_content != null\">AND number_content = #{dto.number_content}</if>" +
			" <if test=\"dto.create != null\">AND create = #{dto.create}</if>" +
			" <if test=\"dto.deleted != null\">AND deleted = #{dto.deleted}</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " </where>" +
            "</script>")
    List<EnshrineGroup> find(@Param("dto") EnshrineGroupDTO dto, @Param("page") Page page);

    @Insert("insert into `e_enshrine_group`(`user_id`,`name`,`number_content`,`create`,`deleted`)  values(#{user_id},#{name},#{number_content},#{create},#{deleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean insert(EnshrineGroup model);

    @Update("update `e_enshrine_group` set user_id=#{user_id},name=#{name},number_content=#{number_content},create=#{create},deleted=#{deleted} where id=#{id}")
    boolean update(EnshrineGroupDTO dto);

}
