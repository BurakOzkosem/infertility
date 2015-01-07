package com.genesearch.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 05.01.2015.
 */
@Entity
@Table(name="Subject")
public class Subject extends AbstractEntity {

    @Id
    @GenericGenerator(name="subject_generator",strategy="increment")
    @GeneratedValue(generator="subject_generator")
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "primary_identifier", length = 200)
    private String primaryIdentifier;

    @Column(name = "symbol", length = 200)
    private String symbol;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "dsc", length = 2000)
    private String dsc;

    @Column(name = "chromosome_name", length = 200)
    private String chromosomeName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public String getChromosomeName() {
        return chromosomeName;
    }

    public void setChromosomeName(String chromosomeName) {
        this.chromosomeName = chromosomeName;
    }
}