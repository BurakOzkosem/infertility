package com.genesearch.object.response;

import com.genesearch.model.OntologyAnnotation;
import com.genesearch.object.edit.GeneEdit;

/**
 * Created by user on 07.01.2015.
 */
public class SearchOntologyAnnotationResponse {

    private UtilResponse utils = new UtilResponse();

    private Long id;
    private String ontologyTermPrimaryIdentifier;
    private String ontologyTermName;

    private String subjectPrimaryIdentifier;
    private String subjectSymbol;
    private String subjectName;
    private String subjectDsc;
    private String subjectChromosomeName;

    private String evidenceBaseAnnotationsSubjectBackgroundName;
    private String evidenceBaseAnnotationsSubjectZygosity;

    private Long publicationId;
    private String publicationDoi;

    private GeneEdit geneEdit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOntologyTermPrimaryIdentifier() {
        return ontologyTermPrimaryIdentifier;
    }

    public void setOntologyTermPrimaryIdentifier(String ontologyTermPrimaryIdentifier) {
        this.ontologyTermPrimaryIdentifier = ontologyTermPrimaryIdentifier;
    }

    public String getOntologyTermName() {
        return ontologyTermName;
    }

    public void setOntologyTermName(String ontologyTermName) {
        this.ontologyTermName = ontologyTermName;
    }

    public String getSubjectPrimaryIdentifier() {
        return subjectPrimaryIdentifier;
    }

    public void setSubjectPrimaryIdentifier(String subjectPrimaryIdentifier) {
        this.subjectPrimaryIdentifier = subjectPrimaryIdentifier;
    }

    public String getSubjectSymbol() {
        return subjectSymbol;
    }

    public void setSubjectSymbol(String subjectSymbol) {
        this.subjectSymbol = subjectSymbol;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDsc() {
        return subjectDsc;
    }

    public void setSubjectDsc(String subjectDsc) {
        this.subjectDsc = subjectDsc;
    }

    public String getSubjectChromosomeName() {
        return subjectChromosomeName;
    }

    public void setSubjectChromosomeName(String subjectChromosomeName) {
        this.subjectChromosomeName = subjectChromosomeName;
    }

    public String getEvidenceBaseAnnotationsSubjectBackgroundName() {
        return evidenceBaseAnnotationsSubjectBackgroundName;
    }

    public void setEvidenceBaseAnnotationsSubjectBackgroundName(String evidenceBaseAnnotationsSubjectBackgroundName) {
        this.evidenceBaseAnnotationsSubjectBackgroundName = evidenceBaseAnnotationsSubjectBackgroundName;
    }

    public String getEvidenceBaseAnnotationsSubjectZygosity() {
        return evidenceBaseAnnotationsSubjectZygosity;
    }

    public void setEvidenceBaseAnnotationsSubjectZygosity(String evidenceBaseAnnotationsSubjectZygosity) {
        this.evidenceBaseAnnotationsSubjectZygosity = evidenceBaseAnnotationsSubjectZygosity;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationDoi() {
        return publicationDoi;
    }

    public void setPublicationDoi(String publicationDoi) {
        this.publicationDoi = publicationDoi;
    }

    public GeneEdit getGeneEdit() {
        return geneEdit;
    }

    public void setGeneEdit(GeneEdit geneEdit) {
        this.geneEdit = geneEdit;
    }

    public UtilResponse getUtils() {
        return utils;
    }

    public void setUtils(UtilResponse utils) {
        this.utils = utils;
    }

    public static SearchOntologyAnnotationResponse create(OntologyAnnotation entity) {
        SearchOntologyAnnotationResponse response = new SearchOntologyAnnotationResponse();

        response.setId(entity.getId());

        if(entity.getEvidence() != null) {
            response.setEvidenceBaseAnnotationsSubjectBackgroundName(entity.getEvidence().getBaseAnnotationsSubjectBackgroundName());
            response.setEvidenceBaseAnnotationsSubjectZygosity(entity.getEvidence().getBaseAnnotationsSubjectZygosity());

            if(entity.getEvidence().getPublication() != null) {
                response.setPublicationId(entity.getEvidence().getPublication().getId());
                response.setPublicationDoi(entity.getEvidence().getPublication().getDoi());
            }
        }
        if(entity.getOntologyTerm() != null) {
            response.setOntologyTermName(entity.getOntologyTerm().getName());
            response.setOntologyTermPrimaryIdentifier(entity.getOntologyTerm().getPrimaryIdentifier());
        }

        if(entity.getSubject() != null) {
            response.setSubjectPrimaryIdentifier(entity.getSubject().getPrimaryIdentifier());
            response.setSubjectSymbol(entity.getSubject().getSymbol());
            response.setSubjectName(entity.getSubject().getName());
            response.setSubjectDsc(entity.getSubject().getDsc());
            response.setSubjectChromosomeName(entity.getSubject().getChromosomeName());
        }



        return response;
    }
}
