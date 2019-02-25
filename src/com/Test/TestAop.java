package com.Test;/*
    user ji
    data 2019/2/25
    time 2:36 PM
*/

import com.aop.MyInvocationHandler;
import com.aop.Source;
import com.aop.SourceAble;

import java.lang.reflect.InvocationHandler;

public class TestAop {
    public static void main(String[] args) {
        System.out.println("-----------动态代理--------------");
        //动态代理测试
        SourceAble sourceAble1 = new Source();//被代理的对象
        InvocationHandler invocationHandler = new MyInvocationHandler(sourceAble1);//
        //代理对象
        SourceAble dynamicProxy = (SourceAble) java.lang.reflect.Proxy.newProxyInstance(sourceAble1.getClass().getClassLoader(),sourceAble1.getClass().getInterfaces(),invocationHandler);
        dynamicProxy.method();

    }
}
