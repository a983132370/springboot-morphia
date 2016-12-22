package com.zhu;

import com.zhu.config.mongodbconfig.prop.MongoProp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序开始入口
 */
@EnableAutoConfiguration
@SpringBootApplication
public class App {

    public static void main( String[] args ){
        SpringApplication.run(App.class, args);
    }
}
