package com.genesearch.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by user on 17.01.2015.
 */
@Entity
@Table(name = "Downloader")
public class JobStatus {

    @Id
    private Integer status;

    public JobStatus() {
    }

    public JobStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
