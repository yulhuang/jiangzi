package com.najiujiangzi.jiangzi.mappers;

import com.najiujiangzi.jiangzi.annotation.RedisCache;
import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.util.Page;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@RedisCache
public interface UserMapper {

    @Select("<script>" +
            " SELECT * FROM sys_user" +
            " <where>" +
            " 1 = 1" +
            " <if test=\"user.id != null\">AND id = #{user.id}</if>" +
            " <if test=\"user.name != null\">AND name = #{user.name}</if>" +
            " <if test=\"user.account != null\">AND account = #{user.account}</if>" +
            " <if test=\"user.gender != null\">AND gender = #{user.gender}</if>" +
            " <if test=\"user.email != null\">AND email = #{user.email}</if>" +
            " <if test=\"user.phone != null\">AND phone = #{user.phone}</if>" +
            " <if test=\"user.likeFind != null\">AND name LIKE concat('%',#{user.likeFind},'%')</if>" +
            " <if test=\"page != null\">LIMIT #{page.startPage}, #{page.pageSize}</if>" +
            " AND deleted = 0" +
            "</where>" +
            "</script>")
    List<User> find(@Param("user") UserDTO dto, @Param("page") Page page);

//    @Insert("insert into sys_user(`name`, `account`, `gender`, `email`, `create`, `deleted`, `password`)  values(#{name},#{account},#{gender},#{email},#{create},#{deleted},#{password})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int insert(User model);

    //动态sql  type:指定一个类    method:使用这个类中的save方法返回的sql字符串  作为sql的语句
    @InsertProvider(type = com.najiujiangzi.jiangzi.mappers.sql.UserSql.class, method = "save")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean insert(User model);

//    @Update("update user set name=#{name} where id=#{id}")
//    int update(UserDTO dto);

    @UpdateProvider(type = com.najiujiangzi.jiangzi.mappers.sql.UserSql.class, method = "updateSql")
    boolean update(User model);

    @Select("<script>" +
            " SELECT * FROM sys_user" +
            " <where>" +
            " 1 = 1" +
            " <if test=\"id != null\">AND id = #{id}</if>" +
            " <if test=\"name != null\">AND name = #{name}</if>" +
            " <if test=\"account != null\">AND account = #{account}</if>" +
            " <if test=\"gender != null\">AND gender = #{gender}</if>" +
            " <if test=\"email != null\">AND email = #{email}</if>" +
            " <if test=\"phone != null\">AND phone = #{phone}</if>" +
            " <if test=\"likeFind != null\">AND name LIKE concat('%',#{likeFind},'%')</if>" +
            " <if test=\"deleted != null\">AND deleted = #{deleted}</if>" +
            " LIMIT 1" +
            "</where>" +
            "</script>")
    UserDTO findOne(UserDTO dto);

    @Select("SELECT u.*, r.* FROM sys_user u left join sys_user_role ur on u.id = ur.user_id left join sys_role r on ur.role_id = r.id where u.id = #{id}")
    List<User> findById(Long id);

    @Select("SELECT * FROM sys_user where (account = #{account} or email = #{email}) AND deleted = 0")
    List<User> registerByNameOrEmail(String name, String email);

    @Update("update sys_user set password=#{password} where id=#{id}")
    boolean updatePassword(UserDTO dto);

    @Select("SELECT MAX(account) FROM sys_user WHERE account LIKE #{prefix}")
    String maxAccount(String prefix);
}
