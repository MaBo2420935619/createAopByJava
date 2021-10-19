package com.mabo.controller;
import com.mabo.aop.AopCutPoint;
import com.mabo.aop.AopType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author mabo
 * @Description   用于控制请求的方法
 */
public class InitSystem {
    public Object initController(String requestType, Map<String,Object> map){
        try {
            //配置包的基本扫描路径
            List<Class> classsFromPackage = PackageUtil.getClasssFromPackage("com.mabo");
            for (Class<?> clazz : classsFromPackage) {
                //获取该类的所有方法
                Method[] methods = clazz.getDeclaredMethods();
                try {
                    try {
                        Controller o = (Controller) clazz.newInstance();//创建该类的对象
                        //遍历方法,判断方法是否带指定的注解,
                        for (Method method : methods) {
                            method.setAccessible(true);//设置方法为可执行的
                            if (method.isAnnotationPresent(RequestMapping.class)) {
                                //获取注解的接口
                                RequestMapping mt = method.getAnnotation(RequestMapping.class);
                                //获取注解的参数
                                String value = mt.value();
                                //带注解的调用运行,并解析注解的属性值输出打印
                                if (value.equals(requestType)) {
                                    //满足条件执行方法
                                    Object invoke = method.invoke(o, map);
                                    return invoke;
                                }
                            }

                        }
                    } catch (Exception e) {
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Object initAop(Map aopMap,Map methodMap){
        try {
            //配置包的基本扫描路径
            List<Class> classsFromPackage = PackageUtil.getClasssFromPackage("com.mabo");
            for (Class<?> clazz : classsFromPackage) {
                //获取该类的所有方法
                Method[] methods = clazz.getDeclaredMethods();
                try {
                    Object o = clazz.newInstance();//创建该类的对象
                    //遍历方法,判断方法是否带指定的注解,
                    for (Method method : methods) {
                        method.setAccessible(true);//设置方法为可执行的
                        if (method.isAnnotationPresent(AopCutPoint.class)) {
                            //获取注解的接口
                            AopCutPoint annotation = method.getAnnotation(AopCutPoint.class);
                            //获取注解的参数
                            String location = annotation.location();
                            String methodName = annotation.methodName();
                            AopType type = annotation.type();
                            Class<?> aClass = Class.forName(location);
                            Method me = aClass.getMethod(methodName,Map.class);
                            //带注解的调用运行,并解析注解的属性值输出打印
                            if (type.equals(AopType.Before)) {
                                //满足条件执行方法
                                me.invoke(o,aopMap);
                                Object invoke = method.invoke(o, methodMap);
                                return invoke;
                            }
                            else if (type.equals(AopType.After)) {
                                //满足条件执行方法
                                Object invoke = method.invoke(o, methodMap);
                                me.invoke(o,aopMap);
                                return invoke;
                            }
                            return null;
                        }
                    }
                } catch (InstantiationException e) {
                } catch (IllegalAccessException e) {
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
