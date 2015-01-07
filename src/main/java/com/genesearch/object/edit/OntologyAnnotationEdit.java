package com.genesearch.object.edit;

import com.genesearch.model.OntologyAnnotation;

/**
 * Created by user on 07.01.2015.
 */
public class OntologyAnnotationEdit {

    private Long id;

    private EvidenceEdit evidenceEdit;
    private OntologyTermEdit ontologyTermEdit;
    private SubjectEdit subjectEdit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvidenceEdit getEvidenceEdit() {
        return evidenceEdit;
    }

    public void setEvidenceEdit(EvidenceEdit evidenceEdit) {
        this.evidenceEdit = evidenceEdit;
    }

    public OntologyTermEdit getOntologyTermEdit() {
        return ontologyTermEdit;
    }

    public void setOntologyTermEdit(OntologyTermEdit ontologyTermEdit) {
        this.ontologyTermEdit = ontologyTermEdit;
    }

    public SubjectEdit getSubjectEdit() {
        return subjectEdit;
    }

    public void setSubjectEdit(SubjectEdit subjectEdit) {
        this.subjectEdit = subjectEdit;
    }

    public static OntologyAnnotationEdit create(OntologyAnnotation entity) {
        OntologyAnnotationEdit response = new OntologyAnnotationEdit();

        response.setId(entity.getId());

        if(entity.getEvidence() != null) {
            response.setEvidenceEdit(EvidenceEdit.create(entity.getEvidence()));
        }

        if(entity.getOntologyTerm() != null) {
            response.setOntologyTermEdit(OntologyTermEdit.create(entity.getOntologyTerm()));
        }

        if(entity.getSubject() != null) {
            response.setSubjectEdit(SubjectEdit.create(entity.getSubject()));
        }

        return response;
    }
}
