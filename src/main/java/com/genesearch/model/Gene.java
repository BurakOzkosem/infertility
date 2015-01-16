package com.genesearch.model;

import com.genesearch.object.request.GeneRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 02.01.2015.
 */
@Entity
@Table(name="Gene")
public class Gene extends AbstractEntity {

    @Id
    @GenericGenerator(name="gene_generator",strategy="increment")
    @GeneratedValue(generator="gene_generator")
    @Column(name = "gene_id")
    private Long id;

    @Column(name="primary_identifier", length = 200)
    private String primaryIdentifier;

    @Column(name="symbol", length = 200)
    private String symbol;

    @Column(name="organism_name", length = 200)
    private String organismName;

    @Column(name="ncbi_id", length = 200)
    private String ncbi;

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

    public String getNcbi() {
        return ncbi;
    }

    public void setNcbi(String ncbi) {
        this.ncbi = ncbi;
    }
}
