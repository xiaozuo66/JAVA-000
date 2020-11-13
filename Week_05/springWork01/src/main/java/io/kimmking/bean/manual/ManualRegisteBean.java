package io.kimmking.bean.manual;


import io.kimmking.bean.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 手动创建bean，注册到spring容器中
 */
public class ManualRegisteBean {

    public static void main(String[] args) {

        /**
         * 使用BeanDefinitionBuilder创建bean
         */
        BeanDefinitionBuilder builder= BeanDefinitionBuilder.genericBeanDefinition(Student.class);

        builder.addPropertyValue("name","左成帅");
        builder.addPropertyValue("age","20");
        builder.addPropertyValue("address","北京");

        BeanDefinition beanDefinition=builder.getBeanDefinition();

        AnnotationConfigApplicationContext content=new AnnotationConfigApplicationContext();
        content.registerBeanDefinition("student",beanDefinition);
        content.refresh();
        System.out.println("使用BeanDefinitionBuilder创建bean"+content.getBean("student"));
    }
}
