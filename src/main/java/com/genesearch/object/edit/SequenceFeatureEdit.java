package com.genesearch.object.edit;

import com.genesearch.model.SequenceFeature;

/**
 * Created by kmorozov on 19.01.2015.
 */
public class SequenceFeatureEdit {

    private Long id;
    private String ontologyTermId;
    private String ontologyTermName;
    private String evidenceWithText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOntologyTermId() {
        return ontologyTermId;
    }

    public void setOntologyTermId(String ontologyTermId) {
        this.ontologyTermId = ontologyTermId;
    }

    public String getOntologyTermName() {
        return ontologyTermName;
    }

    public void setOntologyTermName(String ontologyTermName) {
        this.ontologyTermName = ontologyTermName;
    }

    public String getEvidenceWithText() {
        return evidenceWithText;
    }

    public void setEvidenceWithText(String evidenceWithText) {
        this.evidenceWithText = evidenceWithText;
    }

    public static SequenceFeatureEdit create(SequenceFeature sf) {
        SequenceFeatureEdit newEntity = new SequenceFeatureEdit();
        newEntity.setId(sf.getId());
        newEntity.setOntologyTermId(sf.getPhenotype().getPhenotypeId());
        newEntity.setOntologyTermName(sf.getPhenotype().getName());
        newEntity.setEvidenceWithText(sf.getEvidenceWithText());
        return newEntity;
    }
}
