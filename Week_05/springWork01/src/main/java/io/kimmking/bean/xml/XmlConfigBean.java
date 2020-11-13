package io.kimmking.bean.xml;

import io.kimmking.bean.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 通过XML配置bean
 */
public class XmlConfigBean {

    public static void main(String[] args) {

        /**
         * 1、通过绝对路径加载，使用FileSystemXmlApplicationContext
         */
        String url="D:\\JAVA-000\\Week_05\\springWork01\\src\\main\\resources\\META-INF\\applicationContext.xml";

        FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext(new String[]{url});
        Student student= (Student) context.getBean("student");
        System.out.println("使用FileSystemXmlApplicationContext加载配置文件:"+student);

        /**
         * 2、使用ClassPathXmlApplicationContext
         */
        String classPathFile="META-INF/applicationContext.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext(classPathFile);
        classPathXmlApplicationContext.refresh();
        student=classPathXmlApplicationContext.getBean(Student.class);
        System.out.println("使用ClassPathXmlApplicationContext加载配置文件:"+student);

        /**
         * 3、使用XmlBeanDefinitionReader
         */
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(beanFactory);
        int beanCount=xmlBeanDefinitionReader.loadBeanDefinitions(classPathFile);
        System.out.println("使用XmlBeanDefinitionReader加载配置文件,加载了"+beanCount+"个");

        System.out.println("使用XmlBeanDefinitionReader加载配置文件"+beanFactory.getBean("student"));
    }
}
