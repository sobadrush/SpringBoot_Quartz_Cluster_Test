package com.nanshan.springboot_quartz_cluster_test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class SpringBootQuartzClusterTestApplicationTests {

    @Autowired
    private DataSource ds;

    @Test
    @Disabled
    void test_001() {
        System.out.println("datasource = " + ds);
    }

}
