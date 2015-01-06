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

    @ManyToOne
    @JoinColumn(name = "pubmed_id")
    private Publication publication;

    @Column(name = "b_annot_subj_bckground_name")
    private String baseAnnotationsSubjectBackgroundName;

    @Column(name = "b_annot_subj_zygocity")
    private String baseAnnotationsSubjectZygosity;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
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

}
