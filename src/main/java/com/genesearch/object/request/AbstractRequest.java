package com.genesearch.object.request;

/**
 * Created by user on 05.01.2015.
 */
public abstract class AbstractRequest {

    protected Long id;
    protected String name;
    protected String dsc;

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
}
