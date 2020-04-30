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
 * 该注解将限制该注解下的方法的入参值不能为null或者不能为''（空字符串）。
 *
 * <p>该注解的入参值为String类型，若只需要限制部分入参值，则只需要将需要做限制的参数名
 * 传入并以英文逗号隔开即可，若不传参数则默认限制全部:
 * <pre>
 *     @Null("name,email")
 *     public String xxx(String name, String email, Integer status) {
 *        ...
 *     }
 * </pre>
 *
 * <p>注意事项
 * 该注解不能限制参数内的值，比如Controller层的接口如果用DTO对象作为入参封装多个
 * 参数的值的话，该注解将只会限制此DTO对象不为null。若要限制DTO对象里的属性值，
 * 建议使用SpringSecurity提供的注解：
 *
 * @PreAuthorize("!#dto.name.equals('') and !#dto.email.equals('')")
 *
 * <pre>
 * 错误示例!!!
 *     @Null("name,email")
 *     public String xxx(UserDTO dto) {
 *        ...
 *     }
 * 注解失效。
 * </pre>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Null {
    String value() default "ALL";
}
