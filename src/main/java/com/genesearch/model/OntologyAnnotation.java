package com.genesearch.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 06.01.2015.
 */
@Entity
@Table(name="Ontology_annotation")
public class OntologyAnnotation  extends AbstractEntity {

    @Id
    @GenericGenerator(name="ontology_annotation_generator",strategy="increment")
    @GeneratedValue(generator="ontology_annotation_generator")
    @Column(name = "ontology_annotation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "ontology_term_id")
    private OntologyTerm ontologyTerm;

    @ManyToOne
    @JoinColumn(name = "evidence_id")
    private Evidence evidence;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public OntologyTerm getOntologyTerm() {
        return ontologyTerm;
    }

    public void setOntologyTerm(OntologyTerm ontologyTerm) {
        this.ontologyTerm = ontologyTerm;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }
}
