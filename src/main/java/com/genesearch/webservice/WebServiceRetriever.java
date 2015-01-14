package com.genesearch.webservice;

import java.util.List;

/**
 * Created by user on 06.01.2015.
 */
public interface WebServiceRetriever {

    static final String ROOT = "http://www.mousemine.org/mousemine/service";

    List<List<Object>> execute();

}
