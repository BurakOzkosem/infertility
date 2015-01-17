package com.genesearch.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by kmorozov on 16.01.2015.
 */
public class GeneHomologueKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "gene_id", nullable = false)
    private Gene gene;

    @ManyToOne
    @JoinColumn(name = "homologue_id", nullable = false)
    private Homologue homologue;

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public Homologue getHomologue() {
        return homologue;
    }

    public void setHomologue(Homologue homologue) {
        this.homologue = homologue;
    }
}
