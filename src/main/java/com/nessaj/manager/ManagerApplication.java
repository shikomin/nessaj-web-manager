package com.nessaj.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.nessaj.**.mapper")
public class ManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }

}
