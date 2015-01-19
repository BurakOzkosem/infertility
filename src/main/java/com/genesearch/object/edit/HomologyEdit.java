package com.genesearch.object.edit;

import com.genesearch.model.Homology;

/**
 * Created by kmorozov on 16.01.2015.
 */
public class HomologyEdit extends AbstractEditObject {

    private String primaryIdentifier;
    private String symbol;
    private String organismName;
    private String type;
    private String datasetsName;

    public static HomologyEdit create(Homology entity) {
        HomologyEdit result = new HomologyEdit();
        result.setId(entity.getId());
        result.setPrimaryIdentifier(entity.getPrimaryIdentifier());
        result.setSymbol(entity.getSymbol());
        result.setOrganismName(entity.getOrganismName());
        result.setDatasetsName(entity.getDatasetsName());
        result.setType(entity.getType());
        return result;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatasetsName() {
        return datasetsName;
    }

    public void setDatasetsName(String datasetsName) {
        this.datasetsName = datasetsName;
    }
}
