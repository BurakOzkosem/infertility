package com.genesearch.object.response;

import java.util.Date;

/**
 * Created by user on 17.01.2015.
 */
public class SimpleDateResponse {

    private Date value;

    public SimpleDateResponse(Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}
