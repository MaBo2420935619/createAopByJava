package com.mabo.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author mabo
 * @Description   用于控制请求的注解
 */

@Target({ElementType.METHOD})   //标识只能再方法上使用该注解
@Retention(RetentionPolicy.RUNTIME) //代码执行的时候使用
public @interface RequestMapping {
    String value();
}
