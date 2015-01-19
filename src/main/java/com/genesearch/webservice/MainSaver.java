package com.genesearch.webservice;

import com.genesearch.repository.OntologyAnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by user on 18.01.2015.
 */
@Service
public class MainSaver {

    @Autowired
    private GeneSaver geneSaver;
    @Autowired
    private GeneDetailsSaver geneDetailsSaver;

    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;


    @Transactional(readOnly = false)
    public void execute() {
        geneSaver.execute(new OntologyAnnotationRetriever());
        Set<String> geneIdList = ontologyAnnotationRepository.findAllGenes();

        GeneDetailsRetriever geneDetailsRetriever = new GeneDetailsRetriever();
        for(String geneId : geneIdList) {
            geneDetailsRetriever.setGeneId(geneId);
            geneDetailsSaver.execute(geneDetailsRetriever);
        }
    }
}
