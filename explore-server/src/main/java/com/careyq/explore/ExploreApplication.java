package com.careyq.explore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@MapperScan("com.careyq.explore.server.mapper")
@SpringBootApplication(scanBasePackages = {"com.careyq.explore"})
public class ExploreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExploreApplication.class, args);
    }
}
