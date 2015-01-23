package com.genesearch.webservice;

import java.util.List;

public interface WebServiceRetriever {

    static final String ROOT = "http://www.mousemine.org/mousemine/service";

    List<List<Object>> execute();

}
