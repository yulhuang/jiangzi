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

    /**
     * 调用controller包下的任意类的任意方法时均会调用此方法
     */
//    @Around("execution(* com.najiujiangzi.jiangzi.controller.*.test(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();
        /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("<=====================================================");
        System.out.println("请求来源： =》" + request.getRemoteAddr());
        System.out.println("请求URL：" + request.getRequestURL().toString());
        System.out.println("请求方式：" + request.getMethod());
        System.out.println("响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("请求参数：" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("------------------------------------------------------");*/
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
//        Object result = joinPoint.proceed(args);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return "aop";
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
            //参数名
            //parameters[i].getName();
            //参数类型
            //parameters[i].getType();
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
