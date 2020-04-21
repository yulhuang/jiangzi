package com.najiujiangzi.jiangzi.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 转换类，例如Bean转DTO，DTO转Bean等
 */
public class Transform {

    /**
     * 转换
     *
     * @param o      被转换的类
     * @param tClass 需要的类型.Class
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> T forClass(Object o, Class<T> tClass) throws InvocationTargetException, IllegalAccessException {
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Class<?> aClass = o.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.getName().substring(0, 3).equals("get") && method.invoke(o) != null) {
                Method classMethod;
                try {
                    classMethod = tClass.getMethod("s" + method.getName().substring(1), method.invoke(o).getClass());
                } catch (NoSuchMethodException e) {
                    //tClass类没有该方法，进入下次循环
                    continue;
                }
                classMethod.invoke(t, method.invoke(o));
            }
        }
        return t;
    }

}
