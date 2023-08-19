package com.nanshan.springboot_quartz_cluster_test.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @author RogerLo
 * @date 2023/8/19
 */
@Component
@Slf4j
public class QuartzSchedulerListener implements SchedulerListener {

    @Override
    public void jobScheduled(Trigger trigger) {
        log.info("jobScheduled trigger:" + trigger);
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        log.info("jobUnscheduled triggerKey:" + triggerKey);
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        log.info("triggerFinalized trigger:" + trigger);
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        log.info("triggerPaused triggerKey:" + triggerKey);
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        log.info("triggersPaused triggerGroup:" + triggerGroup);
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        log.info("triggerResumed triggerKey:" + triggerKey);
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        log.info("triggersResumed triggerGroup:" + triggerGroup);
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        log.info("jobAdded jobDetail:" + jobDetail);
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        log.info("jobDeleted jobKey:" + jobKey);
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        log.info("jobPaused jobKey:" + jobKey);
    }

    @Override
    public void jobsPaused(String jobGroup) {
        log.info("jobsPaused jobGroup:" + jobGroup);
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        log.info("jobResumed jobKey:" + jobKey);
    }

    @Override
    public void jobsResumed(String jobGroup) {
        log.info("jobsResumed jobGroup:" + jobGroup);
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        log.info("schedulerError msg:" + cause);
    }

    @Override
    public void schedulerInStandbyMode() {
        log.info("schedulerInStandbyMode");
    }

    @Override
    public void schedulerStarted() {
        log.info("schedulerStarted");
    }

    @Override
    public void schedulerStarting() {
        log.info("schedulerStarting");
    }

    @Override
    public void schedulerShutdown() {
        log.info("schedulerShutdown");
    }

    @Override
    public void schedulerShuttingdown() {
        log.info("schedulerShuttingdown");
    }

    @Override
    public void schedulingDataCleared() {
        log.info("schedulingDataCleared");
    }

}

