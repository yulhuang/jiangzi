package com.najiujiangzi.jiangzi.aop;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.najiujiangzi.jiangzi.annotation.RedisCache;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class RedisCacheAOP {
    private final String MAPPER_URL = "com.najiujiangzi.jiangzi.mappers";

    /**
     * @return void
     * @Title: queryCachePointcut
     * @Description: 定义切点为缓存注解
     **/
    @Pointcut("@within(com.najiujiangzi.jiangzi.annotation.RedisCache)")
    public void queryCachePointcut() {
    }

    @Autowired
    private RedisUtil jedis;

    @Around("queryCachePointcut()")
    public Object cached(ProceedingJoinPoint pjp) {
        String key = this.getCacheKey(pjp);
        String value = jedis.get(key);
        if (!StringUtils.isEmpty(value)) {
            return this.isJSONArray(value, pjp);
        }
        Object retrunObj;
        try {
            retrunObj = pjp.proceed();
            //如果该方法的执行结果为null或者Map类型，则直接返回
            if (retrunObj instanceof Map) {
                return retrunObj;
            } else if (retrunObj instanceof Boolean && (boolean) retrunObj) {
                //如果返回值等于1，则清空该类下的所有缓存
                jedis.delKeys(pjp.getSignature().getDeclaringTypeName() + "*");
                return retrunObj;
            }
            value = retrunObj == null ? "isNull" : JSON.toJSONString(retrunObj);
        } catch (Throwable throwable) {
            throw new RuntimeException("原方法错误；");
        }
        Class<?> inter = null;
        Class<?>[] interfaces = pjp.getTarget().getClass().getInterfaces();
        for (Class<?> anInterface : interfaces)
            if (anInterface.getPackage().getName().equals(MAPPER_URL))
                inter = anInterface;
        if (inter == null)
            return retrunObj;
        RedisCache annotation = inter.getAnnotation(RedisCache.class);
        jedis.setex(key, annotation.value(), value);
        if (retrunObj instanceof List) {
            if (((List) retrunObj).size() > 0) {
                Class<?> entityClazz = ((List) retrunObj).toArray()[0].getClass();
                return JSONArray.parseArray(value, entityClazz);
            }
            return JSONArray.parseArray(value);
        }
        return this.isJSONArray(value, pjp);
    }

    private Object isJSONArray(String value, ProceedingJoinPoint pjp) {
        Object obj = JSONObject.parse(value);
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取方法返回类型
        Type type = signature.getMethod().getGenericReturnType();
        try {
            if (obj instanceof JSONArray) {
                //if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                //判断改集合是否有泛型
                if (type instanceof ParameterizedType) {
                    //list泛型类型,只支持list类型，所以只取第一个泛型
                    Type genericity = ((ParameterizedType) type).getActualTypeArguments()[0];
                    return JSONArray.parseArray(value, (Class) genericity);
                }
                //如果返回结果为list类型且没有泛型则报错
                throw new RuntimeException("该方法返回类型没有加泛型；");
            } else {
                //如果返回类型不为list类型，则直接将结果转为返回值的类型的对象
                return value.equals("isNull") ? "" : JSON.parseObject(value, Class.forName(type.getTypeName()));
            }
        } catch (ClassNotFoundException | JSONException var11) {
            throw new RuntimeException("返回数据报错；");
        }
    }

    private String getCacheKey(ProceedingJoinPoint pjp) {
        StringBuilder buf = new StringBuilder();
        Signature signature = pjp.getSignature();
        //类名
        String declaringTypeName = signature.getDeclaringTypeName();
        //方法名
        String name = signature.getName();
        buf.append(declaringTypeName).append(".").append(name);
        //参数值
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            Object o = JSON.toJSONString(arg);
            buf.append(o);
        }
        return buf.toString();
    }

}

