package com.aop;/*
    user ji
    data 2019/2/25
    time 2:34 PM
*/

public class Source implements SourceAble {
    @Override
    public void method1() {
        System.out.println("this is a method1");
    }

    @Override
    public void method() {
        System.out.println("this is a easy method");
    }
}
