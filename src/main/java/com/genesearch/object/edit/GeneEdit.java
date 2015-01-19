package com.genesearch.object.edit;

import com.genesearch.model.Gene;
import com.genesearch.model.Publication;
import com.genesearch.model.SequenceFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by kmorozov on 16.01.2015.
 */
public class GeneEdit extends AbstractEditObject {

    private String primaryIdentifier;
    private String symbol;
    private String organismName;
    private String ncbi;
    private String phenoTypes;

    private List<HomologyEdit> homologyEditList = new ArrayList<HomologyEdit>();

    private List<SequenceFeatureEdit> sequenceFeatureEditList = new ArrayList<SequenceFeatureEdit>();

    private List<PublicationEdit> publicationEditList = new ArrayList<PublicationEdit>();

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrganismName() {
        return organismName;
    }

    public void setOrganismName(String organismName) {
        this.organismName = organismName;
    }

    public String getNcbi() {
        return ncbi;
    }

    public void setNcbi(String ncbi) {
        this.ncbi = ncbi;
    }

    public List<HomologyEdit> getHomologyEditList() {
        return homologyEditList;
    }

    public void setHomologyEditList(List<HomologyEdit> homologyEditList) {
        this.homologyEditList = homologyEditList;
    }

    public List<SequenceFeatureEdit> getSequenceFeatureEditList() {
        return sequenceFeatureEditList;
    }

    public void setSequenceFeatureEditList(List<SequenceFeatureEdit> sequenceFeatureEditList) {
        this.sequenceFeatureEditList = sequenceFeatureEditList;
    }

    public List<PublicationEdit> getPublicationEditList() {
        return publicationEditList;
    }

    public void setPublicationEditList(List<PublicationEdit> publicationEditList) {
        this.publicationEditList = publicationEditList;
    }

    public String getPhenoTypes() {
        return phenoTypes;
    }

    public void setPhenoTypes(String phenoTypes) {
        this.phenoTypes = phenoTypes;
    }

    public static GeneEdit create(Gene entity, List<SequenceFeature> sequenceFeatureList, Set<String> phenoTypes, Set<Publication> publicationList) {
        GeneEdit result = new GeneEdit();
        result.setPrimaryIdentifier(entity.getPrimaryIdentifier());
        result.setSymbol(entity.getSymbol());
        result.setOrganismName(entity.getOrganismName());
        result.setNcbi(entity.getNcbi());

        result.setPhenoTypes(concatenateWithComma(phenoTypes));

        List<SequenceFeatureEdit> sequenceFeatureEditList = new ArrayList<SequenceFeatureEdit>();
        for(SequenceFeature sf : sequenceFeatureList) {
            sequenceFeatureEditList.add(SequenceFeatureEdit.create(sf));
        }
        result.setSequenceFeatureEditList(sequenceFeatureEditList);

        List<PublicationEdit> publicationEditList = new ArrayList<PublicationEdit>();
        for(Publication publication : publicationList) {
            publicationEditList.add(PublicationEdit.create(publication));
        }
        result.setPublicationEditList(publicationEditList);

        return  result;
    }

    public static GeneEdit createBrief(Gene entity) {
        GeneEdit result = new GeneEdit();
        result.setPrimaryIdentifier(entity.getPrimaryIdentifier());
        result.setSymbol(entity.getSymbol());
        result.setOrganismName(entity.getOrganismName());
        result.setNcbi(entity.getNcbi());
        return  result;
    }

    private static String concatenateWithComma(Set<String> strings) {
        StringBuilder sb = new StringBuilder();
        for(String s : strings) {
            sb.append(s.trim()).append(", ");
        }
        if(sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }
}
