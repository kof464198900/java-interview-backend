package com.interview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Java面试题应用启动类
 */
@SpringBootApplication
@MapperScan("com.interview.mapper")
public class InterviewApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }
}
