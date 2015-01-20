package com.genesearch.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by kmorozov on 19.01.2015.
 */
@Entity
@Table(name="Sequence_feature")
public class SequenceFeature extends AbstractEntity{

    @Id
    @GenericGenerator(name="sequence_feature_generator",strategy="increment")
    @GeneratedValue(generator="sequence_feature_generator")
    @Column(name = "sequence_feature_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_id")
    private Gene gene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phenotype_id")
    @Filter(name="phenotype_sequence_feature",condition="type = 'SEQUENCE_FEATURE'")
    private Phenotype phenotype;

    @Column(name="evidence_with_text", length = 200)
    private String evidenceWithText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getEvidenceWithText() {
        return evidenceWithText;
    }

    public void setEvidenceWithText(String evidenceWithText) {
        this.evidenceWithText = evidenceWithText;
    }

    public Phenotype getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(Phenotype phenotype) {
        this.phenotype = phenotype;
    }

}
