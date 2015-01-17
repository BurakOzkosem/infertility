package com.genesearch.scheduler;

import com.genesearch.repository.JobStatusRepository;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by user on 17.01.2015.
 */
@Component
public class ScheduleInformator {

    private static final Logger log = LoggerFactory.getLogger(ScheduleInformator.class);

    @Autowired
    private JobStatusRepository jobStatusRepository;

    public JobStatus getStatus() {

        try {

            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();

            for(JobExecutionContext jcontext : scheduler.getCurrentlyExecutingJobs()) {
                if(jcontext.getJobDetail().getKey().getName().equalsIgnoreCase(DownloaderJob.jobName)) {
                    return JobStatus.WORKING;
                }
            }

        } catch (SchedulerException e) {
            log.error(e.toString(), e);
        }

        return jobStatusRepository.getStatus();
    }

    public Date getNextStartDate() {
        try {
            CronExpression cron = new CronExpression(DownloaderJob.cron);
            return cron.getTimeAfter(new Date());
        } catch (ParseException e) {
            log.error(e.toString(), e);
        }
        return null;
    }

    public void startTask() {
        try {

            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();

            for(JobExecutionContext jcontext : scheduler.getCurrentlyExecutingJobs()) {
                if(jcontext.getJobDetail().getKey().getName().equalsIgnoreCase(DownloaderJob.jobName)) {
                    return;
                }
            }

            scheduler.triggerJob(new JobKey(DownloaderJob.jobName, DownloaderJob.groupName));

        } catch (SchedulerException e) {
            log.error(e.toString(), e);
        }
    }
}
