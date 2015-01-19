package com.genesearch.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 06.01.2015.
 */
@Entity
@Table(name="Evidence")
public class Evidence extends AbstractEntity {

    @Id
    @GenericGenerator(name="evidence_generator",strategy="increment")
    @GeneratedValue(generator="evidence_generator")
    @Column(name = "evidence_id")
    private Long id;

    @Column(name = "pubmed_id")
    private Long pubmedId;

    @Column(name = "b_annot_subj_bckground_name", length = 200)
    private String baseAnnotationsSubjectBackgroundName;

    @Column(name = "b_annot_subj_zygocity", length = 200)
    private String baseAnnotationsSubjectZygosity;

    @Column(name = "doi", length = 200)
    private String doi;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseAnnotationsSubjectBackgroundName() {
        return baseAnnotationsSubjectBackgroundName;
    }

    public void setBaseAnnotationsSubjectBackgroundName(String baseAnnotationsSubjectBackgroundName) {
        this.baseAnnotationsSubjectBackgroundName = baseAnnotationsSubjectBackgroundName;
    }

    public String getBaseAnnotationsSubjectZygosity() {
        return baseAnnotationsSubjectZygosity;
    }

    public void setBaseAnnotationsSubjectZygosity(String baseAnnotationsSubjectZygosity) {
        this.baseAnnotationsSubjectZygosity = baseAnnotationsSubjectZygosity;
    }

    public Long getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(Long pubmedId) {
        this.pubmedId = pubmedId;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }
}
