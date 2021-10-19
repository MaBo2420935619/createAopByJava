package com.mabo.aop;

import com.mabo.controller.InitSystem;

import java.util.Map;

public class AopTest {
    public void testBefore(Map map){
        System.out.println("这是切面testBefore");
    }
    public void testAfter(Map map){
        System.out.println("这是切面testAfter");
    }

//    @AopCutPoint(location = "com.mabo.aop.AopTest",methodName = "testAfter",type = AopType.After)
@AopCutPoint(location = "com.mabo.aop.AopTest",methodName = "testBefore",type = AopType.Before)
    public String aop(Map map){
        System.out.println("这是切面");
        return "这是切面的返回值";
    }

    public static void main(String[] args) {
        InitSystem initSystem=new InitSystem();
        //参数为空调用  /hello 修饰的方法
        Object o = initSystem.initAop(null,null);
        System.out.println(o);
    }
}
