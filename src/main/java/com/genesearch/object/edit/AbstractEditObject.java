package com.genesearch.object.edit;

import com.genesearch.object.response.UtilResponse;

/**
 * Created by user on 03.01.2015.
 */
public abstract class AbstractEditObject {

    protected Long id;

    protected UtilResponse utils = new UtilResponse();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtilResponse getUtils() {
        return utils;
    }

    public void setUtils(UtilResponse utils) {
        this.utils = utils;
    }

}
