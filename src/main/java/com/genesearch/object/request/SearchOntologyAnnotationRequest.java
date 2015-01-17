package com.genesearch.object.request;


import com.genesearch.object.Page;

/**
 * Created by user on 03.01.2015.
 */
public class SearchOntologyAnnotationRequest extends Page {

    private Long id;
    private Long ontologyTermId;
    private String ontologyTermPrimaryIdentifier;
    private String ontologyTermName;

    private String subjectPrimaryIdentifier;
    private String subjectSymbol;
    private String subjectName;
    private String subjectDsc;
    private String subjectChromosomeName;

    private String evidenceBaseAnnotationsSubjectBackgroundName;
    private String evidenceBaseAnnotationsSubjectZygosity;

    private String publicationId;
    private String publicationDoi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOntologyTermId() {
        return ontologyTermId;
    }

    public void setOntologyTermId(Long ontologyTermId) {
        this.ontologyTermId = ontologyTermId;
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

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationDoi() {
        return publicationDoi;
    }

    public void setPublicationDoi(String publicationDoi) {
        this.publicationDoi = publicationDoi;
    }
}
