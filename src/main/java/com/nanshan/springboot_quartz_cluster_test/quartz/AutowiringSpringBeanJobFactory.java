package com.nanshan.springboot_quartz_cluster_test.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @author RogerLo
 * @date 2023/8/19
 *
 * 讓 JOB 可以使用 @Autowired 注入
     * ref.http://samchu.logdown.com/posts/297038-use-quartz-in-spring-boot
 */
@Slf4j
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private AutowireCapableBeanFactory beanFactory;

    // public AutowireCapableBeanJobFactory(AutowireCapableBeanFactory beanFactory) {
    //     Assert.notNull(beanFactory, "Bean factory must not be null");
    //     this.beanFactory = beanFactory;
    // }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("... ApplicationContextAware 注入 applicationContext: {}", (applicationContext != null));
        this.beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        this.beanFactory.autowireBean(job);
        return job;
    }

}
