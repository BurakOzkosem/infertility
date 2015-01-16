package com.genesearch.object.edit;

import com.genesearch.model.OntologyTerm;

/**
 * Created by user on 07.01.2015.
 */
public class OntologyTermEdit extends AbstractEditObject {

    private String name;
    private String primaryIdentifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    public static OntologyTermEdit create(OntologyTerm entity) {
        OntologyTermEdit editObject = new OntologyTermEdit();
        editObject.setId(entity.getId());
        editObject.setPrimaryIdentifier(entity.getPrimaryIdentifier());
        editObject.setName(entity.getName());
        return editObject;
    }
}
