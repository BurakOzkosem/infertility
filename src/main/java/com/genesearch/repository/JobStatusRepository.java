package com.genesearch.repository;

import com.genesearch.scheduler.JobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 17.01.2015.
 */
@Repository
public class JobStatusRepository extends ModelRepository<com.genesearch.model.JobStatus>{

    private static final Logger log = LoggerFactory.getLogger(JobStatusRepository.class);


    public JobStatus getStatus() {
        List<com.genesearch.model.JobStatus> statuses = (List<com.genesearch.model.JobStatus>) getAll();

        if(statuses.size() > 1) {
            log.error("More then one status stored in database");
        }

        if(statuses.size() == 1) {
            return JobStatus.getByCode(statuses.get(0).getStatus());
        }

        return null;
    }

    public void setStatus(JobStatus newStatus) {
        List<com.genesearch.model.JobStatus> statuses = (List<com.genesearch.model.JobStatus>) getAll();

        if(statuses.size() > 1) {
            log.error("More then one status stored in database");
        }

        if(statuses.size() == 1) {
            delete(statuses.get(0));
        }

        save(new com.genesearch.model.JobStatus(newStatus.getCode()));
    }
}
