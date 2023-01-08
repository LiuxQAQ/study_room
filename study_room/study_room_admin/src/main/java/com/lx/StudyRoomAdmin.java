package com.lx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lx.mapper")
public class StudyRoomAdmin {
    public static void main(String[] args) {
        SpringApplication.run(StudyRoomAdmin.class,args);
    }
}
