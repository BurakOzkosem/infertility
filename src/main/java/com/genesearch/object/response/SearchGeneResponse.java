package com.genesearch.object.response;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;

/**
 * Created by user on 07.01.2015.
 */
public class SearchGeneResponse {

    private UtilResponse utils = new UtilResponse();

    private Long id;
    private Long geneId;
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

    private String ncbi;

//    private GeneEdit geneEdit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGeneId() {
        return geneId;
    }

    public void setGeneId(Long geneId) {
        this.geneId = geneId;
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

    public String getNcbi() {
        return ncbi;
    }

    public void setNcbi(String ncbi) {
        this.ncbi = ncbi;
    }

    public UtilResponse getUtils() {
        return utils;
    }

    public void setUtils(UtilResponse utils) {
        this.utils = utils;
    }

    public static SearchGeneResponse create(OntologyAnnotation entity) {
        SearchGeneResponse response = new SearchGeneResponse();

        response.setId(entity.getId());
        response.setGeneId(entity.getGene().getId());

        response.setSubjectPrimaryIdentifier(entity.getGene().getPrimaryIdentifier());
        response.setSubjectSymbol(entity.getGene().getSymbol());
        response.setSubjectName(entity.getGene().getName());
        response.setSubjectDsc(entity.getGene().getDsc());
        response.setSubjectChromosomeName(entity.getGene().getChromosome());
        response.setNcbi(entity.getGene().getNcbi());

        if(entity.getPhenotype() != null) {
            response.setOntologyTermName(entity.getPhenotype().getName());
            response.setOntologyTermPrimaryIdentifier(entity.getPhenotype().getPhenotypeId());
        }

        response.setEvidenceBaseAnnotationsSubjectBackgroundName(entity.getBaseAnnotationsSubjectBackgroundName());
        response.setEvidenceBaseAnnotationsSubjectZygosity(entity.getBaseAnnotationsSubjectZygosity());
        response.setPublicationId(entity.getPubmedId());
        response.setPublicationDoi(entity.getDoi());

        return response;
    }
}
