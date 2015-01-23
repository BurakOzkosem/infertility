package com.genesearch.object.edit;

/**
 * This object is convenient for displaying genotype data and literatire data on separate tabs
 */
public class GeneAnnotationEdit {

    private GenotypeEdit genotypeEdit;
    private LiteratureEdit literatureEdit;

    public GenotypeEdit getGenotypeEdit() {
        return genotypeEdit;
    }

    public void setGenotypeEdit(GenotypeEdit genotypeEdit) {
        this.genotypeEdit = genotypeEdit;
    }

    public LiteratureEdit getLiteratureEdit() {
        return literatureEdit;
    }

    public void setLiteratureEdit(LiteratureEdit literatureEdit) {
        this.literatureEdit = literatureEdit;
    }
}
