package com.beanpro;/*
    user ji
    data 2019/2/25
    time 8:26 AM
*/

public class Property {
    public String name;
    public String value;
    public String ref;

    public Property() {
    }
    public Property(String name,String value,String ref){
        this.name = name;
        this.value = value;
        this.ref = ref;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
