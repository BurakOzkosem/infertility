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
