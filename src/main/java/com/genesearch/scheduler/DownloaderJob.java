package com.genesearch.scheduler;

/**
 * Created by user on 17.01.2015.
 */

import com.genesearch.domain.GeneDetailsSaver;
import com.genesearch.domain.MouseMineSaver;
import com.genesearch.repository.JobStatusRepository;
import com.genesearch.webservice.GeneDetailsRetriever;
import com.genesearch.webservice.MouseMineRetriever;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class DownloaderJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(DownloaderJob.class);

    public static final String jobName = "downloadJob";
    public static final String groupName = "downloadGroup";
    public static final String triggerName = "downloadTrigger";

    public static final String cron = "0 0 1 1 * ?";


    @Transactional(readOnly = false)
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        GeneDetailsSaver geneDetailsSaver = (GeneDetailsSaver) jobExecutionContext.getJobDetail().getJobDataMap().get("geneDetailsSaver");
        MouseMineSaver mouseMineSaver = (MouseMineSaver) jobExecutionContext.getJobDetail().getJobDataMap().get("mouseMineSaver");
        JobStatusRepository jobStatusRepository = (JobStatusRepository) jobExecutionContext.getJobDetail().getJobDataMap().get("jobStatusRepository");

        try {
            geneDetailsSaver.execute(new GeneDetailsRetriever());
            mouseMineSaver.execute(new MouseMineRetriever());
        } catch (Exception e) {
            log.error(e.toString(), e);
            jobStatusRepository.setStatus(JobStatus.FAILED);
            return;
        }

        jobStatusRepository.setStatus(JobStatus.SUCCESS);
    }
}
