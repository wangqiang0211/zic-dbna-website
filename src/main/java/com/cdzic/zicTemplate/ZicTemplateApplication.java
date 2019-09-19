package com.cdzic.zicTemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@MapperScan("com.cdzic.zicTemplate.dao.mapper")
//public class ZicTemplateApplication extends SpringBootServletInitializer{
public class ZicTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZicTemplateApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(ZicTemplateApplication.class);
//    }

}
