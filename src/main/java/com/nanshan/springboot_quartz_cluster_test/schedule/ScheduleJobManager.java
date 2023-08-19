package com.nanshan.springboot_quartz_cluster_test.schedule;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author RogerLo
 * @date 2023/8/19
 *
 * 本類別作用：
 *     1. 啟動 scheduler
 *     2. 並初始化 job
 */
@Component
@Slf4j
public class ScheduleJobManager {

    // 加入 Qualifier 注解，通過名稱注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Autowired
    private MonitorJob monitorJob;

    @PostConstruct
    public void init() {
        // 啟動調度器
        try {
            if (scheduler != null && !scheduler.isStarted()) {
                scheduler.start();
                log.info("ScheduleJobManager 任務調度服務啟動成功!!");

                // 把 job 添加到 scheduler 中執行
                this.registerScheduleJob(this.buildMyScheduleJob());
            }
        } catch (Exception e) {
            log.error("ScheduleJobManager 任務調度服務啟動出錯,err=", e);
        }
    }

    private ScheduleJob buildMyScheduleJob() {
        // 構建任務
        String jobName = "monitorJobProxy";

        //【1】
        JobDetail jobDetail = JobBuilder.newJob(MonitorJobProxy.class) // 指定 Proxy job class
            .withIdentity(jobName,"group1") // 設置job的唯一標識符(group預設會是 DEFAULT)
            .withDescription("this is Test Job") // 設置job 描述信息
            .storeDurably(false) // 設置當沒有Trigger關聯job時是否繼續存儲job. 設置為false時, 會同時刪除job; 默認為false
            .requestRecovery(false) // 在程序恢覆或故障轉移時, 是否重新執行job, 默認為false
            .usingJobData("author", "Roger Lo") // 設置攜帶數據, 在job觸發時可以獲取
            .usingJobData("year", "2023")
            .build();

        //【2】
        // 傳遞 job，不傳遞的話可以在 MonitorJobProxy 中直接寫入 MonitorJob 中的處理過程
        jobDetail.getJobDataMap().put("monitorJob", monitorJob);

        // 表達式調度構建器(即任務執行的時間)
        CronScheduleBuilder scheduleBuilder
                = CronScheduleBuilder.cronSchedule("30 * * * * ?");

        // 按新的 cronExpression 表達式構建一個新的 trigger
        // 不指定 TriggerKey 的 Group，預設會加入 DEFAULT Group
        //【3】
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(new TriggerKey(jobName))
                .withDescription("this is a test Trigger")
                .withSchedule(scheduleBuilder)
                .build();

        // SimpleTrigger用來創建簡單調度
        // SimpleTrigger trigger = TriggerBuilder.newTrigger()
        //        .withIdentity(jobName)
        //        .startAt(new Date())
        //        .withSchedule(
        //                SimpleScheduleBuilder.simpleSchedule()
        //                        .withIntervalInSeconds(1)
        //                        .withRepeatCount(0)
        //        .build();
        return new ScheduleJob(jobDetail, trigger);
    }

    /**
     * 添加調度任務
     */
    private void registerScheduleJob(ScheduleJob scheduleJob) {
        JobDetail jobDetail = scheduleJob.getJobDetail();
        Trigger trigger = scheduleJob.getTrigger();
        String jobName = jobDetail.getKey().getName();
        try {
            // (Type 1) 添加調度任務, 如果job已經存在, 則覆蓋更新job
            // 需搭配 factoryBean.setOverwriteExistingJobs(true) 才會更新 QRTZ_CRON_TRIGGERS 表格
            scheduler.scheduleJob(jobDetail, Set.of(trigger), true);

            // (Type 2) 添加調度任務, 根據job的名稱和分組判斷, 如果job已經存在時, 拋出異常
            // 需搭配 factoryBean.setOverwriteExistingJobs(true) 才會更新 QRTZ_CRON_TRIGGERS 表格
            // scheduler.scheduleJob(jobDetail, trigger);
            // scheduler.rescheduleJob(trigger.getKey(), trigger); // 有此設置，才能在 SpringBoot 重啟時動態將 DB 中的 QRTZ_CRON_TRIGGERS 表格更新
            log.info("註冊 Job: {} 成功!", jobName);
        } catch (Exception e) {
            log.info("註冊 Job: {} 失敗! Error：{}", jobName, e.getMessage());
        }
    }
}



