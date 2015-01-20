package com.genesearch.object.edit;

import com.genesearch.model.Gene;
import com.genesearch.object.response.SearchGeneResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by kmorozov on 16.01.2015.
 */
public class GeneEdit extends AbstractEditObject {

    private String primaryIdentifier;
    private String symbol;
    private String name;
    private String dsc;
    private String chromosome;
    private String ncbi;

    private String organismName;

    private List<HomologyEdit> homologyEditList = new ArrayList<HomologyEdit>();

    private List<SequenceFeatureEdit> sequenceFeatureEditList = new ArrayList<SequenceFeatureEdit>();

    private List<SearchGeneResponse> searchGeneResponseList = new ArrayList<SearchGeneResponse>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
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

    public List<SearchGeneResponse> getSearchGeneResponseList() {
        return searchGeneResponseList;
    }

    public void setSearchGeneResponseList(List<SearchGeneResponse> searchGeneResponseList) {
        this.searchGeneResponseList = searchGeneResponseList;
    }

    public static GeneEdit create(Gene entity) {
        GeneEdit result = new GeneEdit();

        result.setPrimaryIdentifier(entity.getPrimaryIdentifier());
        result.setSymbol(entity.getSymbol());
        result.setName(entity.getName());
        result.setDsc(entity.getDsc());
        result.setChromosome(entity.getChromosome());
        result.setNcbi(entity.getNcbi());
        result.setOrganismName(entity.getOrganismName());

        return  result;
    }

//    public static GeneEdit createBrief(Gene entity) {
//        GeneEdit result = new GeneEdit();
//        result.setPrimaryIdentifier(entity.getPrimaryIdentifier());
//        result.setSymbol(entity.getSymbol());
//        result.setOrganismName(entity.getOrganismName());
//        result.setNcbi(entity.getNcbi());
//        return  result;
//    }

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
