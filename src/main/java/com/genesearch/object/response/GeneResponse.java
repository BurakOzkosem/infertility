package com.genesearch.object.response;

import com.genesearch.model.Gene;
import com.genesearch.object.edit.AbstractEditObject;

/**
 * Created by user on 03.01.2015.
 */
public class GeneResponse extends AbstractEditObject {

    public static GeneResponse create(Gene entity) {
        GeneResponse response = new GeneResponse();

        response.setId(entity.getId());
        response.setName(entity.getName());

        return response;
    }

}
