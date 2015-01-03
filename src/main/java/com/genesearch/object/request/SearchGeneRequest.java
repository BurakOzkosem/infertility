package com.genesearch.object.request;


import com.genesearch.object.Page;

/**
 * Created by user on 03.01.2015.
 */
public class SearchGeneRequest extends Page {

    private Long id;
    private String name;
    private String dsc;

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
