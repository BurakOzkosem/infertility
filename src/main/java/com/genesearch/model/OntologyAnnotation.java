package com.genesearch.model;

import com.genesearch.object.edit.OntologyAnnotationEdit;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *  Every attribute commented with query field names used to download data from Mousemine.org
 * */
@Entity
@Table(name="Ontology_annotation")
public class OntologyAnnotation extends  AbstractEntity {

    @Id
    @GenericGenerator(name="ontology_annotation_generator",strategy="increment")
    @GeneratedValue(generator="ontology_annotation_generator")
    @Column(name = "ontology_annotation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_id")
    private Gene gene;

    // OntologyAnnotation.evidence.publications.pubMedId
    @Column(name = "pubmed_id", length = 200)
    private String pubmedId;

    // OntologyAnnotation.evidence.baseAnnotations.subject.background.name
    @Column(name = "b_annot_subj_bckground_name", length = 200)
    private String baseAnnotationsSubjectBackgroundName;

    // OntologyAnnotation.evidence.baseAnnotations.subject.zygosity
    @Column(name = "b_annot_subj_zygocity", length = 200)
    private String baseAnnotationsSubjectZygosity;

    // OntologyAnnotation.evidence.publications.doi
    @Column(name = "doi", length = 200)
    private String doi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phenotype_id")
    @Filter(name="phenotype_gene",condition="type = 'GENE'")
    private Phenotype phenotype;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(String pubmedId) {
        this.pubmedId = pubmedId;
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

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Phenotype getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(Phenotype phenotype) {
        this.phenotype = phenotype;
    }

    public void update(OntologyAnnotationEdit oaEdit, Phenotype phenotype, Gene gene) {
        setPhenotype(phenotype);
        setGene(gene);
        setBaseAnnotationsSubjectBackgroundName(oaEdit.getEvidenceBaseAnnotationsSubjectBackgroundName());
        setBaseAnnotationsSubjectZygosity(oaEdit.getEvidenceBaseAnnotationsSubjectZygosity());
        setPubmedId(oaEdit.getPublicationId());
        setDoi(oaEdit.getPublicationDoi());
    }
}
