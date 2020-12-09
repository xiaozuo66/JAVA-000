package com.homework.demo.xa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

@MapperScan("com.tyj.**.mapper")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ShardingJdbcXaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcXaApplication.class, args);
    }

}
