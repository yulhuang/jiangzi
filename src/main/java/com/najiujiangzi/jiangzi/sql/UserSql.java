package com.najiujiangzi.jiangzi.sql;

import com.najiujiangzi.jiangzi.model.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * 动态创建sql类
 */
public class UserSql {
    //方法中的关键字是区分大小写的  SQL SELECT WHERE
    public String save(User user) {
        return new SQL() {
            {
                INSERT_INTO("sys_user");
                if (user.getName() != null) {
                    VALUES("`name`", "#{name}");
                }
                if (user.getAccount() != null) {
                    VALUES("`account`", "#{account}");
                }
                if (user.getGender() != null) {
                    VALUES("`gender`", "#{gender}");
                }
                if (user.getEmail() != null) {
                    VALUES("`email`", "#{email}");
                }
                if (user.getCreate() != null) {
                    VALUES("`create`", "#{create}");
                }
                if (user.getDeleted() != null) {
                    VALUES("`deleted`", "#{deleted}");
                }
                if (user.getPassword() != null) {
                    VALUES("`password`", "#{password}");
                }
                if (user.getPhone() != null) {
                    VALUES("`phone`", "#{phone}");
                }
                if (user.getBirthday() != null) {
                    VALUES("`birthday`", "#{birthday}");
                }
            }
        }.toString();
    }

    public String updateSql(User model) {
        if (model.getId() == null) {
            throw new RuntimeException("id不能为空");
        }
        return new SQL() {
            {
                //@Update("update user set name=#{name} where id=#{id}")
                UPDATE("sys_user");
                if (model.getName() != null) {
                    SET("`name` = #{name}");
                }
                if (model.getAccount() != null) {
                    SET("`account` = #{account}");
                }
                if (model.getGender() != null) {
                    SET("`gender` = #{gender}");
                }
                if (model.getEmail() != null) {
                    SET("`email` = #{email}");
                }
                if (model.getCreate() != null) {
                    SET("`create` = #{create}");
                }
                if (model.getDeleted() != null) {
                    SET("`deleted` = #{deleted}");
                }
                if (model.getPassword() != null) {
                    SET("`password` = #{password}");
                }
                if (model.getPhone() != null) {
                    SET("`phone` = #{phone}");
                }
                if (model.getBirthday() != null) {
                    SET("`birthday` = #{birthday}");
                }
                WHERE("`id` = #{id}");
            }
        }.toString();
    }
}
