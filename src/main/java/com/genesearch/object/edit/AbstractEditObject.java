package com.genesearch.object.edit;

import com.genesearch.object.response.UtilResponse;

/**
 * Created by user on 03.01.2015.
 */
public abstract class AbstractEditObject {

    protected Long id;
    protected String name;
    protected String dsc;

    protected UtilResponse utils = new UtilResponse();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UtilResponse getUtils() {
        return utils;
    }

    public void setUtils(UtilResponse utils) {
        this.utils = utils;
    }

}
