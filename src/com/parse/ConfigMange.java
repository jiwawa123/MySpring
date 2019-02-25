package com.parse;/*
    user ji
    data 2019/2/25
    time 9:12 AM
*/

import com.beanpro.Bean;
import com.beanpro.Property;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMange {
    //解析xml文件,将所有的bean静态生成
    public static Map<String,Bean> parseXml(String path) throws Exception{
        Map<String,Bean> map = new HashMap<>();
        SAXReader reader = new SAXReader();

        InputStream is = BeanFactoryImp.class.getResourceAsStream(path);
        Document doc = reader.read(is);
        String xpath = "//bean";
        List<Element> list = doc.selectNodes(xpath);
        for (Element beanElm:list
                ) {
            List<Property> pLi = new ArrayList<>();
            String name = beanElm.attributeValue("name");
            String className = beanElm.attributeValue("class");
            Bean bean = new Bean(name,className);
            List<Element> proElement = beanElm.selectNodes("property");
            for (Element pro:proElement
                    ) {
                String pname = pro.attributeValue("name");
                String pvalue = pro.attributeValue("value");
                String pref = pro.attributeValue("ref");
                pLi.add(new Property(pname,pvalue,pref));
            }
            bean.setProperties(pLi);
            map.put(name,bean);
        }
        return map;
    }
    //根据对象以及对象的属性名称注入方法
    //使用内省技术来实现类方法
    public static Method getWriteMethod(Object bean,String name){
        Method method = null;
        try{
            BeanInfo beanInfo  = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();

            if(pro!=null){
                for (PropertyDescriptor pd:pro
                     ) {
                    if(pd.getName().equals(name)){
                        method =  pd.getWriteMethod();
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("类出错");
        }
        if(method ==null){
            throw new RuntimeException("方法没有找到");
        }
        return method;
    }
}
