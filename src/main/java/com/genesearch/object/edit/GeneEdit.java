package com.genesearch.object.edit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmorozov on 16.01.2015.
 */
public class GeneEdit extends AbstractEditObject {

    private String primaryIdentifier;
    private String symbol;
    private String organismName;
    private String ncbi;

    private List<HomologueEdit> homologueEditList = new ArrayList<HomologueEdit>();

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

    public List<HomologueEdit> getHomologueEditList() {
        return homologueEditList;
    }

    public void setHomologueEditList(List<HomologueEdit> homologueEditList) {
        this.homologueEditList = homologueEditList;
    }
}
