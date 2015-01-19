package com.genesearch.model;

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

    @ManyToOne
    @JoinColumn(name = "gene_id")
    private Gene gene;

    @Column(name="ontology_term_id", length = 200)
    private String ontologyTermId;

    @Column(name="ontology_term_name", length = 200)
    private String ontologyTermName;

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

    public String getOntologyTermId() {
        return ontologyTermId;
    }

    public void setOntologyTermId(String ontologyTermId) {
        this.ontologyTermId = ontologyTermId;
    }

    public String getOntologyTermName() {
        return ontologyTermName;
    }

    public void setOntologyTermName(String ontologyTermName) {
        this.ontologyTermName = ontologyTermName;
    }

    public String getEvidenceWithText() {
        return evidenceWithText;
    }

    public void setEvidenceWithText(String evidenceWithText) {
        this.evidenceWithText = evidenceWithText;
    }
}
