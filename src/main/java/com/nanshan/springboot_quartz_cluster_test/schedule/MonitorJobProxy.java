package com.nanshan.springboot_quartz_cluster_test.schedule;

import org.quartz.*;

/**
 * @author RogerLo
 * @date 2023/8/19
 *
 * @DisallowConcurrentExecution: 默認情況下, quartz 的定時任務是可以並發執行的, 也就是說如果本次定時任務調度還沒有執行完(耗時比較長), 又到了下一個觸發時間點, 那麽會再次創建一個Job實例, 然後執行execute方法. quartz 提供了注解 @DisallowConcurrentExecution, 用於禁止定時任務並發執行. 但是需要注意的是, 雖然 @DisallowConcurrentExecution 注解時添加到自定義Job上的, 但是並發性時控制在 JobDetail 級別的. 也就是說, 如果一個Job配置了多個JobDetail, 那麽多個JobDetail 之間可以並發執行, 但是單個JobDetail 不能並發執行.
 * @PersistJobDataAfterExecution: 當job 正常執行沒有拋出異常時, 更新Job存儲在JobDataMap中數據, 以便下次觸發時獲取
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MonitorJobProxy implements Job {

    private MonitorJob monitorJob;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.err.println(" >>> MonitorJobProxy Execute >>>");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        monitorJob = (MonitorJob) jobDataMap.get("monitorJob");  // 這邊若開啟 spring-boot-devtools 會轉型失敗，應該是原生 BUG
        monitorJob.work(jobExecutionContext);
    }
}