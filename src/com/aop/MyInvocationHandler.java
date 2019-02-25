package com.aop;/*
    user ji
    data 2019/2/25
    time 2:35 PM
*/

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target; //们既然要做代理，我们必须知道我们是给谁做代理，这里的obj就是被代理者。

    MyInvocationHandler() {
        super();
    }

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        atfer();
        return result;
    }

    private void atfer() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}

