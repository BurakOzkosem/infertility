package com.genesearch.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by kmorozov on 16.01.2015.
 */
public class GeneHomologyKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "gene_id", nullable = false)
    private Gene gene;

    @ManyToOne
    @JoinColumn(name = "homologue_id", nullable = false)
    private Homology homology;

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
}
