package com.genesearch;

import com.genesearch.domain.GeneDetailsSaver;
import com.genesearch.domain.MouseMineSaver;
import com.genesearch.repository.JobStatusRepository;
import com.genesearch.scheduler.DownloaderJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.*;

/**
 * Created by user on 17.01.2015.
 */

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

    @Autowired
    private GeneDetailsSaver geneDetailsSaver;
    @Autowired
    private MouseMineSaver mouseMineSaver;
    @Autowired
    private JobStatusRepository jobStatusRepository;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() != null) {
            return;
        }

        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            Scheduler scheduler = sf.getScheduler();

            JobDetail job = newJob(DownloaderJob.class)
                    .withIdentity(DownloaderJob.jobName, DownloaderJob.groupName)
                    .storeDurably()
                    .build();

            job.getJobDataMap().put("geneDetailsSaver", geneDetailsSaver);
            job.getJobDataMap().put("mouseMineSaver", mouseMineSaver);
            job.getJobDataMap().put("jobStatusRepository", jobStatusRepository);

            Trigger trigger = newTrigger()
                    .withIdentity(DownloaderJob.triggerName, DownloaderJob.groupName)
                    .withSchedule(cronSchedule(DownloaderJob.cron))
                    .forJob(job)
                    .build();

            scheduler.scheduleJob(job, trigger);

            scheduler.start();
        } catch (SchedulerException e) {
            log.error(e.toString(), e);
        }

    }
}