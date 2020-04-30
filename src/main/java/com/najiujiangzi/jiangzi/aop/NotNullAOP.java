package com.najiujiangzi.jiangzi.aop;

import com.najiujiangzi.jiangzi.annotation.Null;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class NotNullAOP {

    @Pointcut("@annotation(com.najiujiangzi.jiangzi.annotation.Null)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void before(JoinPoint joinPoint) throws NoSuchFieldException {
        //System.out.println("方法执行前执行......before");
        Map<String, Object> parameterMap = new HashMap<>();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Null annotation = method.getAnnotation(Null.class);
        //获取注解中的value值,并去掉所有空格
        String value = annotation.value().replaceAll("\\s*", "");
        List<String> name = Arrays.asList(value.split(","));
        //参数值
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            parameterMap.put(parameters[i].getName(), list.get(i));
        }
        if (value.equals("ALL")) {
            if (list.contains(null) || list.contains("")) {
                throw new RuntimeException("存在为空参数；");
            }
        } else {
            for (int i = 0; i < name.size(); i++) {
                if (parameterMap.containsKey(name.get(i)) && parameterMap.get(name.get(i)) == null
                        || parameterMap.containsKey(name.get(i)) &&  "".equals(parameterMap.get(name.get(i)))) {
                    throw new RuntimeException(name.get(i) + "参数不能为空");
                }
            }
        }
    }
}
