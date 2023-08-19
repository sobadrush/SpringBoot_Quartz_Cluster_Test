package com.nanshan.springboot_quartz_cluster_test.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author RogerLo
 * @date 2023/8/19
 * <p>
 * 批次任務真正的邏輯寫在此
 */
@Component
@Slf4j
public class MonitorJob implements Serializable {

    // @Autowired
    // private ScheduleJobManager scheduleManagement;

    public String getJobName() {
        return "Im MonitorJob";
    }

    public void work(JobExecutionContext jobExecutionContext) {
        log.info(">>> MonitorJob 任務開始" + "11111111111111111");
        log.info(">>> MonitorJob 任務開始" + "11111111111111111");
        log.info(">>> MonitorJob 任務開始" + "11111111111111111");
        // log.info(">>> MonitorJob 任務開始" + "22222222222222222");
        // log.info(">>> MonitorJob 任務開始" + "22222222222222222");
        // log.info(">>> MonitorJob 任務開始" + "22222222222222222");
        String author = jobExecutionContext.getJobDetail().getJobDataMap().getString("author");
        String year = jobExecutionContext.getJobDetail().getJobDataMap().getString("year");
        log.info("@@@@@@@@@@@@@@@@@@@@@@");
        log.info("我是 usingJobData 設置的值: author = {}", author);
        log.info("我是 usingJobData 設置的值: year = {}", year);
        log.info("@@@@@@@@@@@@@@@@@@@@@@");
        log.info(">>> MonitorJob 任務結束");
    }

}