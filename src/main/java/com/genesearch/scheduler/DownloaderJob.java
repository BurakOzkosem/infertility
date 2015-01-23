package com.genesearch.scheduler;

import com.genesearch.repository.JobStatusRepository;
import com.genesearch.webservice.MainSaver;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  The main class, responsible for downloading data from external datasources and saving it to database
 * */
public class DownloaderJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(DownloaderJob.class);

    public static final String jobName = "downloadJob";
    public static final String groupName = "downloadGroup";
    public static final String triggerName = "downloadTrigger";

    public static final String cron = "0 0 1 1 * ?";


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        MainSaver mainSaver = (MainSaver) jobExecutionContext.getJobDetail().getJobDataMap().get("mainSaver");
        JobStatusRepository jobStatusRepository = (JobStatusRepository) jobExecutionContext.getJobDetail().getJobDataMap().get("jobStatusRepository");

        try {

            mainSaver.execute();

        } catch (Exception e) {
            log.error(e.toString(), e);
            jobStatusRepository.setStatus(JobStatus.FAILED);
            return;
        }

        jobStatusRepository.setStatus(JobStatus.SUCCESS);
    }
}
