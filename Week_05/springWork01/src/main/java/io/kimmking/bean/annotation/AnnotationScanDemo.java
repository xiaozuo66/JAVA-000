package io.kimmking.bean.annotation;

import io.kimmking.bean.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过注解扫描的方式加载bean
 */
public class AnnotationScanDemo {

    public static void main(String[] args) {

        String basePackage="io.kimmking.bean";

        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(basePackage);
        //在构造方法种已经调用了refresh方法
//        annotationConfigApplicationContext.refresh();
        System.out.println("通过AnnotationConfigApplicationContext扫描注解:"+annotationConfigApplicationContext.getBean(Student.class));
    }
}
