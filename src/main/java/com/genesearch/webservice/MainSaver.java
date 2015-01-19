package com.genesearch.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by user on 18.01.2015.
 */
@Service
public class MainSaver {

    @Autowired
    private OntologyAnnotationSaver ontologyAnnotationSaver;
    @Autowired
    private GeneDetailsSaver geneDetailsSaver;


    @Transactional(readOnly = false)
    public void execute() {
        ontologyAnnotationSaver.execute(new OntologyAnnotationRetriever());
        geneDetailsSaver.execute(new GeneDetailsRetriever());
    }
}
