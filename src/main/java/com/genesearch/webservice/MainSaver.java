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
    private MouseMineSaver mouseMineSaver;
    @Autowired
    private GeneDetailsSaver geneDetailsSaver;


    @Transactional(readOnly = false)
    public void execute() {
        mouseMineSaver.execute(new MouseMineRetriever());
        geneDetailsSaver.execute(new GeneDetailsRetriever());
    }
}
