package com.Test;/*
    user ji
    data 2019/2/25
    time 8:57 AM
*/

import com.parse.BeanFactory;
import com.parse.BeanFactoryImp;
import com.spring.A;
import com.spring.B;

public class Test {
    public static void main(String[] args) throws Exception{
        BeanFactory factory = new BeanFactoryImp("/application.xml");
        A a = (A)factory.getBean("A");
        B b = (B)factory.getBean("B");
        System.out.println(b.getA().name);
    }
}
