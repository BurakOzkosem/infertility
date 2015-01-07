package com.genesearch.object.edit;


import com.genesearch.model.Subject;

/**
 * Created by user on 07.01.2015.
 */
public class SubjectEdit extends AbstractEditObject {

    private String primaryIdentifier;
    private String symbol;
    private String chromosomeName;

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

    public String getChromosomeName() {
        return chromosomeName;
    }

    public void setChromosomeName(String chromosomeName) {
        this.chromosomeName = chromosomeName;
    }

    public static SubjectEdit create(Subject entity) {
        SubjectEdit objectEdit = new SubjectEdit();
        objectEdit.setId(entity.getId());
        objectEdit.setName(entity.getName());
        objectEdit.setDsc(entity.getDsc());
        objectEdit.setPrimaryIdentifier(entity.getPrimaryIdentifier());
        objectEdit.setSymbol(entity.getSymbol());
        objectEdit.setChromosomeName(entity.getChromosomeName());
        return  objectEdit;
    }
}
