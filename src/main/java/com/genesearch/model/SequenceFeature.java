package com.genesearch.model;

import com.genesearch.object.edit.SequenceFeatureEdit;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *  Every attribute commented with query field names used to download data from Mousemine.org
 * */
@Entity
@Table(name="Sequence_feature")
public class SequenceFeature extends AbstractEntity{

    @Id
    @GenericGenerator(name="sequence_feature_generator",strategy="increment")
    @GeneratedValue(generator="sequence_feature_generator")
    @Column(name = "sequence_feature_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_id", nullable = false)
    private Gene gene;

    // SequenceFeature.ontologyAnnotations.ontologyTerm.identifier
    @Column(name="phenotype_id", length = 200, nullable = false)
    private String phenotypeId;

    // SequenceFeature.ontologyAnnotations.ontologyTerm.name
    @Column(name="phenotype_name", length = 200, nullable = false)
    private String phenotypeName;

    // SequenceFeature.ontologyAnnotations.evidence.withText
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

    public String getPhenotypeId() {
        return phenotypeId;
    }

    public void setPhenotypeId(String phenotypeId) {
        this.phenotypeId = phenotypeId;
    }

    public String getPhenotypeName() {
        return phenotypeName;
    }

    public void setPhenotypeName(String phenotypeName) {
        this.phenotypeName = phenotypeName;
    }


    public void update(Gene gene, SequenceFeatureEdit sequenceFeatureEdit) {
        this.setGene(gene);
        this.setPhenotypeId(sequenceFeatureEdit.getOntologyTermId());
        this.setPhenotypeName(sequenceFeatureEdit.getOntologyTermName());
        this.setEvidenceWithText(sequenceFeatureEdit.getEvidenceWithText());
    }
}
