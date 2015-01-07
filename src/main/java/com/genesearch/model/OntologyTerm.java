package com.genesearch.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 06.01.2015.
 */
@Entity
@Table(name="Ontology_term")
public class OntologyTerm extends AbstractEntity {

    @Id
    @GenericGenerator(name="ontology_term_generator",strategy="increment")
    @GeneratedValue(generator="ontology_term_generator")
    @Column(name = "ontology_term_id")
    private Long id;

    @Column(name = "primary_identifier", length = 200)
    private String primaryIdentifier;

    @Column(name = "name", length = 200)
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }
}
