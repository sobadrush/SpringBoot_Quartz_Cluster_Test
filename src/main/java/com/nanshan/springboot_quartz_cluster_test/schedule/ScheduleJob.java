package com.nanshan.springboot_quartz_cluster_test.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * @author RogerLo
 * @date 2023/8/19
 *
 * 自訂的 ScheduleJob，用來封裝
 *  1. JobDetail
 *  2. Trigger
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ScheduleJob {
    private JobDetail jobDetail;
    private Trigger trigger;
}
