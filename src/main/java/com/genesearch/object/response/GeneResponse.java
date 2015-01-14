package com.genesearch.object.response;

import com.genesearch.model.Gene;
import com.genesearch.object.edit.AbstractEditObject;

/**
 * Created by user on 03.01.2015.
 */
public class GeneResponse extends AbstractEditObject {

    private String primaryIdentifier;
    private String symbol;
    private String organismName;
    private String ncbi;

    public static GeneResponse create(Gene entity) {
        GeneResponse response = new GeneResponse();

        response.setId(entity.getId());
        response.setPrimaryIdentifier(entity.getPrimaryIdentifier());
        response.setSymbol(entity.getSymbol());
        response.setOrganismName(entity.getOrganismName());
        response.setNcbi(entity.getNcbi());

        return response;
    }

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
}
