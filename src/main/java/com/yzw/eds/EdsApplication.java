package com.yzw.eds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan
@SpringBootApplication
public class EdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdsApplication.class, args);
    }

}
