package io.kimmking.springboot.config;


import io.kimmking.springboot.bean.School;
import io.kimmking.springboot.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//标记为配置类
@Configuration
//属性转换类
@EnableConfigurationProperties(FirstDemoProperty.class)
//启用条件
@ConditionalOnProperty(prefix = "first", value = "enabled", havingValue = "true")
@PropertySource("classpath:application.properties")
//@PropertySource("classpath:application.properties")
public class FirstAutoConfiguration {

    @Autowired
    private FirstDemoProperty firstDemoProperty;

    @Bean
    public void test(){
        System.out.println("自动配置的学生信息："+firstDemoProperty.getStudent());
        System.out.println("自动配置的学校信息："+firstDemoProperty.getSchool());
        System.out.println("自动配置的教室信息："+firstDemoProperty.getKlass());
    }
}
