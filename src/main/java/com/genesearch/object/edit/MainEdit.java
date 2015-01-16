package com.genesearch.object.edit;

/**
 * Created by kmorozov on 16.01.2015.
 */
public class MainEdit {

    private OntologyAnnotationEdit ontologyAnnotationEdit;
    private GeneEdit geneEdit;

    public MainEdit() {
    }

    public MainEdit(OntologyAnnotationEdit ontologyAnnotationEdit, GeneEdit geneEdit) {
        this.ontologyAnnotationEdit = ontologyAnnotationEdit;
        this.geneEdit = geneEdit;
    }

    public OntologyAnnotationEdit getOntologyAnnotationEdit() {
        return ontologyAnnotationEdit;
    }

    public void setOntologyAnnotationEdit(OntologyAnnotationEdit ontologyAnnotationEdit) {
        this.ontologyAnnotationEdit = ontologyAnnotationEdit;
    }

    public GeneEdit getGeneEdit() {
        return geneEdit;
    }

    public void setGeneEdit(GeneEdit geneEdit) {
        this.geneEdit = geneEdit;
    }
}
