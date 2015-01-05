package com.genesearch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by user on 06.01.2015.
 */
@Entity
@Table(name="Ontology_term")
public class OntologyTerm extends AbstractEntity {

    @Id
    @Column(name = "ontology_term_id")
    private Long id;

    @Column(name = "primary_identifier")
    private String primary_identifier;

    @Column(name = "name")
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
}
