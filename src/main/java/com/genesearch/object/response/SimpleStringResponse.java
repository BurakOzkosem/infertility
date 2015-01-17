package com.genesearch.object.response;

/**
 * Created by user on 17.01.2015.
 */
public class SimpleStringResponse {

    private String value;

    public SimpleStringResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
