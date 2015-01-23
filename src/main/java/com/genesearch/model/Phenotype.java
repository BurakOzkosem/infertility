package com.genesearch.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *  Every attribute commented with query field names used to download data from Mousemine.org
 * */
@Entity
@Table(name = "Phenotype")
public class Phenotype extends AbstractEntity{

    @Id
    @GenericGenerator(name="phenotype_generator",strategy="increment")
    @GeneratedValue(generator="phenotype_generator")
    @Column(name="id")
    private Long id;

    // OntologyAnnotation.ontologyTerm.identifier
    @Column(name="phenotype_id", length = 200)
    private String phenotypeId;

    // OntologyAnnotation.ontologyTerm.name
    @Column(name="name", length = 200)
    private String name;

    @Column(name="type", length = 25, nullable = false)
    private String type;

    public Phenotype() {
    }

    public Phenotype(String type) {
        this.type = type;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPhenotypeId() {
        return phenotypeId;
    }

    public void setPhenotypeId(String phenotypeId) {
        this.phenotypeId = phenotypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
