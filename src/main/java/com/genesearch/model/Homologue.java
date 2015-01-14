package com.genesearch.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 14.01.2015.
 */
@Entity
@Table(name="Homologue")
public class Homologue extends AbstractEntity {

    @Id
    @GenericGenerator(name="homologue_generator",strategy="increment")
    @GeneratedValue(generator="homologue_generator")
    @Column(name = "homologue_id")
    private Long id;

    @Column(name="primary_identifier", length = 200)
    private String primaryIdentifier;

    @Column(name="symbol", length = 200)
    private String symbol;

    @Column(name="organism_name", length = 200)
    private String organismName;

    @Column(name="type", length = 200)
    private String type;

    @Column(name="datasets_name", length = 200)
    private String datasetsName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrganismName() {
        return organismName;
    }

    public void setOrganismName(String organismName) {
        this.organismName = organismName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatasetsName() {
        return datasetsName;
    }

    public void setDatasetsName(String datasetsName) {
        this.datasetsName = datasetsName;
    }
}
