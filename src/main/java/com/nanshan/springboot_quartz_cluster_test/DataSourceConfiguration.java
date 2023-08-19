package com.nanshan.springboot_quartz_cluster_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author RogerLo
 * @date 2023/8/19
 *
 * Quartz 需搭配連線池，否側會報錯
 */
@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }

}