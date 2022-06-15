package com.example.springbootnocv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.springbootnocv.mapper")
@EnableScheduling
public class SpringbootNocvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNocvApplication.class, args);
    }

}
