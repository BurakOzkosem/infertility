package com.genesearch.model;

import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.request.GeneRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name="name", length = 200) //
    private String name;

    @Column(name="dsc", length = 2000) //
    private String dsc;

    @Column(name="organism_name", length = 200)
    private String organismName;

    @Column(name="ncbi_id", length = 200)
    private String ncbi;

    @ManyToOne
    @JoinColumn(name = "phenotype_id")
    private Phenotype phenotype;

    @Column(name="chromosome", length = 200)
    private String chromosome;



//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gene", cascade = CascadeType.ALL)
//    private Set<GeneHomologue> geneHomologueSet = new HashSet<GeneHomologue>();

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

    public Phenotype getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(Phenotype phenotype) {
        this.phenotype = phenotype;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

//    public Set<GeneHomologue> getGeneHomologueSet() {
//        return geneHomologueSet;
//    }
//
//    public void setGeneHomologueSet(Set<GeneHomologue> geneHomologueSet) {
//        this.geneHomologueSet = geneHomologueSet;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Gene gene = (Gene) o;
//
//        if (this.geneHomologueSet != null ? !this.geneHomologueSet.equals(gene.geneHomologueSet) : gene.geneHomologueSet != null)
//            return false;
//        if (this.ncbi != null ? !this.ncbi.equals(gene.ncbi) : gene.ncbi != null) return false;
//        if (this.organismName != null ? !this.organismName.equals(gene.organismName) : gene.organismName != null) return false;
//        if (this.primaryIdentifier != null ? !this.primaryIdentifier.equals(gene.primaryIdentifier) : gene.primaryIdentifier != null)
//            return false;
//        if (this.symbol != null ? !this.symbol.equals(gene.symbol) : gene.symbol != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = this.primaryIdentifier != null ? this.primaryIdentifier.hashCode() : 0;
//        result = 31 * result + (this.symbol != null ? this.symbol.hashCode() : 0);
//        result = 31 * result + (this.organismName != null ? this.organismName.hashCode() : 0);
//        result = 31 * result + (this.ncbi != null ? this.ncbi.hashCode() : 0);
//        result = 31 * result + (this.geneHomologueSet != null ? this.geneHomologueSet.hashCode() : 0);
//        return result;
//    }

    public void update(GeneEdit geneEdit) {
        this.setPrimaryIdentifier(geneEdit.getPrimaryIdentifier());
        this.setSymbol(geneEdit.getSymbol());
        this.setOrganismName(geneEdit.getOrganismName());
        this.setNcbi(geneEdit.getNcbi());
    }
}
