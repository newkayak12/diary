package com.server.base.common.apiInspectionGenerator;

import org.reflections.Reflections;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class ApiInspectionGenerator {
    public static void inspection(){
        Reflections reflections = new Reflections("com.server.base.controller");
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(RestController.class);
        StringBuilder builder = new StringBuilder();
        set.stream().forEach(v->{
            Method[] methods = v.getMethods();
            Arrays.stream(methods).forEach(i->{
                String methodName = i.getName();
                Arrays.stream(i.getAnnotations()).forEach(l->{
                    if(l.toString().contains("@org.springframework.web.bind.annotation.")){
                        String annotation = l.toString();
                        String method = annotation.substring(0, annotation.indexOf("(")).replaceAll("@org.springframework.web.bind.annotation.","");
                        String url = annotation.substring(annotation.indexOf("value={")+"value={".length(), annotation.lastIndexOf(",")-1);
                        String result = "name : \""+methodName+"\",  method : \""+method+"\", url : "+url;
                        System.out.println(result);
                    }
                });


            });

        });

    }
}
