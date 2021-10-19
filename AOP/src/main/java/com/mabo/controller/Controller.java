package com.mabo.controller;

import java.util.Map;
/**
 * @Author mabo
 * @Description   所有的controller统一参数为 Map<String,Object>
 *                否则会执行失败
 */
public class Controller {

    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        System.out.println("hello");
        return "hello";
    }
    @RequestMapping("/bye")
    public String bye(Map<String,Object> map){
        Object man = map.get("man");
        System.out.println("我要说bye的人是"+man);
        return "bye";
    }



}
