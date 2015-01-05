package com.genesearch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by user on 06.01.2015.
 */
@Entity
@Table(name="Publication")
public class Publication extends AbstractEntity {

    @Id
    @Column(name = "pubmed_id")
    private Long id;

    @Column(name = "doi")
    private String doi;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }
}
