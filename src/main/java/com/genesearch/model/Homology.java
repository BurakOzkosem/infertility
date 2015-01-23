package com.genesearch.model;

import com.genesearch.object.edit.HomologyEdit;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *  Every attribute commented with query field names used to download data from Mousemine.org
 * */
@Entity
@Table(name="Homologue")
public class Homology extends AbstractEntity {

    @Id
    @GenericGenerator(name="homologue_generator",strategy="increment")
    @GeneratedValue(generator="homologue_generator")
    @Column(name = "homologue_id")
    private Long id;

    // Gene.homologues.homologue.primaryIdentifier
    @Column(name="primary_identifier", length = 200)
    private String primaryIdentifier;

    // Gene.homologues.homologue.symbol
    @Column(name="symbol", length = 200)
    private String symbol;

    // Gene.homologues.homologue.organism.name
    @Column(name="organism_name", length = 200)
    private String organismName;

    // Gene.homologues.type
    @Column(name="type", length = 200)
    private String type;

    // Gene.homologues.dataSets.name
    @Column(name="datasets_name", length = 200)
    private String datasetsName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_id")
    private Gene gene;

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

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public void update(Gene gene, HomologyEdit homologyEdit) {
        this.setGene(gene);
        this.setPrimaryIdentifier(homologyEdit.getPrimaryIdentifier());
        this.setSymbol(homologyEdit.getSymbol());
        this.setOrganismName(homologyEdit.getOrganismName());
        this.setType(homologyEdit.getType());
        this.setDatasetsName(homologyEdit.getDatasetsName());
    }
}
