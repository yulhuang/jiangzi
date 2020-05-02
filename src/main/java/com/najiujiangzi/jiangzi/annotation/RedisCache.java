package com.najiujiangzi.jiangzi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created: 2020-4-30
 *
 * <p>注解说明
 * 这是一个用Redis自动缓存数据库查询结果的注解，自动将mappers中数据库查询的结果缓存在Redis中，
 * 包括参数为DTO那样的动态查询方法，如果Redis中有你正要查询的结果，将不会调用mybatis查询数据库，
 * 自动直接从Redis中取值，这样减少了数据库压力且提高了项目运行速度。
 *
 * <p>该注解可自定义缓存时间，参数类型为int类型，时间单位为SECONDS(秒):
 * <pre>
 *    @RedisCache(36000)
 *    public interface xxxMapper {
 *        ...
 *    }
 * </pre>
 *
 * <p>注意事项
 * 若该注解没有设置缓存时间，那么默认缓存时间为1800秒。
 * 若数据库查询结果为null时，Redis也会进行缓存，这样可以防止缓存穿透。
 * 如果该注解下的接口的方法的返回类型为boolean类型并且值为true时，将会清除该接口下的所有缓存，int类型则不会。
 * 所以mappers接口中的新增和修改方法必须用boolean类型作为返回值，不然就会出现缓存数据和数据库数据不一样的错误。
 *
 * <p>以下几种情况将不做缓存：
 * com.najiujiangzi.jiangzi.mappers包以外的类上使用注解；
 * 方法返回值为null或返回类型为Map类型；
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {
    int value() default 1800;
}