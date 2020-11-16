package io.kimmking.springboot.config;


import io.kimmking.springboot.bean.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//标记为配置类
@Configuration
@ConditionalOnClass(School.class)
//属性转换类
@EnableConfigurationProperties(FirstDemoProperty.class)
//启用条件
@ConditionalOnProperty(prefix = "first", value = "enabled", havingValue = "true")
//@PropertySource("classpath:application.yml")
public class FirstAutoConfiguration {

    @Bean
    public School test(){
        return new School();
    }
}
