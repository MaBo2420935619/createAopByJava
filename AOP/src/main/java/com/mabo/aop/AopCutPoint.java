package com.mabo.aop;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author mabo
 * @Description 用于定义切点
 *
 */

@Target({ElementType.METHOD})   //标识只能再方法上使用该注解
@Retention(RetentionPolicy.RUNTIME) //代码执行的时候使用
public @interface AopCutPoint {
    //切点的类名及其路径 com.mabo.Controller
    public String location();
    //方法名称
    public String methodName();
    //切入点再切面的之前还是之后
    public AopType type();
}
