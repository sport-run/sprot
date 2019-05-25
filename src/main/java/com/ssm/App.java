package com.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by Administrator on 2017/7/20.
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.ssm.**.mapper"})
@ServletComponentScan
public class App extends SpringBootServletInitializer {
    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
