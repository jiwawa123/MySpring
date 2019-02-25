package com.beanpro;/*
    user ji
    data 2019/2/25
    time 8:24 AM
*/

import java.util.List;

public class Bean {
    public String name;
    public String className;
    public List<Property> properties;
    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }


    public Bean() {
    }
    public Bean(String name,String className){
        this.name = name;
        this.className = className;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
