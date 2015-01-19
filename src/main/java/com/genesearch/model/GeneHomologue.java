package com.genesearch.model;

import javax.persistence.*;

/**
 * Created by kmorozov on 16.01.2015.
 */
@Entity
@Table(name="Gene_homologue")
@IdClass(GeneHomologueKey.class)
public class GeneHomologue {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_id", nullable = false)
    private Gene gene;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homologue_id", nullable = false)
    private Homology homology;

    public GeneHomologue() {
    }

    public GeneHomologue(Gene gene, Homology homology) {
        this.gene = gene;
        this.homology = homology;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public Homology getHomology() {
        return homology;
    }

    public void setHomology(Homology homology) {
        this.homology = homology;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        GeneHomologue that = (GeneHomologue) o;
//
//        if (!gene.equals(that.gene)) return false;
//        if (!homologue.equals(that.homologue)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = gene.hashCode();
//        result = 31 * result + homologue.hashCode();
//        return result;
//    }
}
