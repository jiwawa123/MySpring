package com.aop;/*
    user ji
    data 2019/2/25
    time 2:35 PM
*/

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target; //我们既然要做代理，我们必须知道我们是给谁做代理，这里的obj就是被代理者。

    MyInvocationHandler() {
        super();
    }
    //将对象传输过来 通过构造函数进行注入
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }
    /*
    * 通过反射机制实现在method.invoke()执行前后，对这个方法进行相应的包装
    * */
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

