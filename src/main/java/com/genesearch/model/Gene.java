package com.genesearch.model;

import com.genesearch.object.edit.GeneEdit;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *  Every attribute commented with query field names used to download data from Mousemine.org
 * */
@Entity
@Table(name="Gene")
public class Gene extends AbstractEntity {

    @Id
    @GenericGenerator(name="gene_generator",strategy="increment")
    @GeneratedValue(generator="gene_generator")
    @Column(name = "gene_id")
    private Long id;

    // Gene.primaryIdentifier
    @Column(name="primary_identifier", length = 200)
    private String primaryIdentifier;

    // OntologyAnnotation.subject.symbol
    @Column(name="symbol", length = 200)
    private String symbol;

    // OntologyAnnotation.subject.name
    @Column(name="name", length = 200)
    private String name;

    // OntologyAnnotation.subject.description
    @Column(name="dsc", length = 2000)
    private String dsc;

    // Gene.organism.name
    @Column(name="organism_name", length = 200)
    private String organismName;

    // Gene.ncbiGeneNumber
    @Column(name="ncbi_id", length = 200)
    private String ncbi;

    // OntologyAnnotation.subject.chromosome.name
    @Column(name="chromosome", length = 200)
    private String chromosome;

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

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public void update(GeneEdit geneEdit) {
        this.setPrimaryIdentifier(geneEdit.getPrimaryIdentifier());
        this.setSymbol(geneEdit.getSymbol());
        this.setName(geneEdit.getName());
        this.setDsc(geneEdit.getDsc());
        this.setOrganismName(geneEdit.getOrganismName());
        this.setNcbi(geneEdit.getNcbi());
        this.setChromosome(geneEdit.getChromosome());
    }

    public static Gene create(GeneEdit geneEdit) {
        Gene gene = new Gene();
        gene.update(geneEdit);
        return  gene;
    }

}
