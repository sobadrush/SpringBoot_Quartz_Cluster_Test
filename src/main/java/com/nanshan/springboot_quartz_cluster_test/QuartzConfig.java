package com.nanshan.springboot_quartz_cluster_test;

import com.nanshan.springboot_quartz_cluster_test.quartz.AutowiringSpringBeanJobFactory;
import com.nanshan.springboot_quartz_cluster_test.quartz.QuartzSchedulerListener;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author RogerLo
 * @date 2023/8/19
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private QuartzSchedulerListener quartzSchedulerListener;

    @Bean
    public AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory() {
        AutowiringSpringBeanJobFactory autowireJobFactory = new AutowiringSpringBeanJobFactory();
        return autowireJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(AutowiringSpringBeanJobFactory autowireJobFactory) throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setSchedulerListeners(quartzSchedulerListener);
        factoryBean.setJobFactory(autowireJobFactory);
        factoryBean.setOverwriteExistingJobs(true); // spring.quartz.overwrite-existing-jobs = true
        factoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        factoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        return factoryBean;
    }

    @Bean(name = "Scheduler")
    @DependsOn({ "schedulerFactoryBean" })
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
        return schedulerFactoryBean.getScheduler();
    }
}
