package com.mabo;

import com.mabo.controller.InitSystem;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String requestType="/hello";
        String requestType2="/bye";
        InitSystem initSystem=new InitSystem();
        //参数为空调用  /hello 修饰的方法
        Object o = initSystem.initController(requestType,null);
        System.out.println("代码执行返回值1:"+o);
        Map map=new HashMap();
        map.put("man", "mabo");
        //参数不为空调用  /bye 修饰的方法
        Object o2 = initSystem.initController(requestType2,map);
        System.out.println("代码执行返回值2:"+o2);
    }
}
