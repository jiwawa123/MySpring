package com.parse;/*
    user ji
    data 2019/2/25
    time 8:29 AM
*/

import com.beanpro.Bean;
import com.beanpro.Property;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanFactoryImp implements BeanFactory {
    Map<String, Object> map = new HashMap();
    Map<String, Bean> config;

    @Override
    public Object getBean(String name) {
        return map.get(name);
    }

    //初始化的时候直接读取配置文件生成相应bean
    public BeanFactoryImp(String path) {
        try {
            config = ConfigMange.parseXml(path);
            //获取配置信息中的bean信息
            for (Map.Entry<String, Bean> en : config.entrySet()
                    ) {
                String name = en.getKey();
                Bean bean = en.getValue();
                Object ob = map.get(name);
                //如果存在bean就不在创建了
                if (ob != null)
                    continue;
                //根据配置初始化bean
                Object obj = creatBean(bean);
                map.put(name, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件路径有误");
        }

    }

    public Object creatBean(Bean bean) {
        /*
        创建bean的class，同时将属性信息注入
         */
        Class clazz = null;
        try {
            clazz = Class.forName(bean.getClassName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(bean.name + "类的位置信息错误");
        }
        //将class对象创建
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(bean.name + "缺乏空参构造方法");
        }
        //属性注入
        /*
        1.简单属性注入
        2.依赖注入
         */
        if (bean.getProperties() != null) {
            for (Property pro : bean.getProperties()
                    ) {
                //根据属性名称获取对应的set方法
                String name = pro.getName();
                Method setMemethod = ConfigMange.getWriteMethod(obj, name);
                Object param = null;
                if (pro.getValue() != null) {
                    param = pro.getValue();
                }
                if (pro.getRef() != null) {
                    Object ob = map.get(pro.getRef());
                    //此时依赖的类还没有生成
                    if (ob == null) {
                        ob = creatBean(config.get(pro.getRef()));
                        //将创建好的bean放入容器中
                        map.put(pro.getRef(), ob);
                    }
                    param = ob;
                }
                try {
                    setMemethod.invoke(obj, param);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("调用方法出错");
                }
            }
        }
        return obj;
    }


}
