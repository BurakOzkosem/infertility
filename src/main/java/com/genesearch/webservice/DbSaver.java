package com.genesearch.webservice;

import com.genesearch.webservice.WebServiceRetriever;

/**
 * Created by user on 06.01.2015.
 */
public interface DbSaver {

    void execute(WebServiceRetriever retriever) throws Exception;

}
