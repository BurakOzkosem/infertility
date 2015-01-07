package com.genesearch.object.edit;

import com.genesearch.model.Publication;

/**
 * Created by user on 07.01.2015.
 */
public class PublicationEdit extends AbstractEditObject {

    private String doi;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public static PublicationEdit create(Publication entity) {
        PublicationEdit editObject = new PublicationEdit();
        editObject.setId(entity.getId());
        editObject.setDoi(entity.getDoi());
        return editObject;
    }
}
