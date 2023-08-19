package com.nanshan.springboot_quartz_cluster_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringBootQuartzClusterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuartzClusterTestApplication.class, args);
    }

}
