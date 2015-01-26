package com.genesearch.webservice;

import com.genesearch.repository.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class MainSaver {

    @Autowired
    private GeneSaver geneSaver;
    @Autowired
    private HomologySaver homologySaver;
    @Autowired
    private SequenceFeatureSaver sequenceFeatureSaver;

    @Autowired
    private GeneRepository geneRepository;


    @Transactional(readOnly = false)
    public void execute() {
        geneSaver.execute(new GeneRetriever());
        Set<String> geneIdList = geneRepository.findAllGenes();

        HomologyRetriever homologyRetriever = new HomologyRetriever();
        SequenceFeatureRetriever sequenceFeatureRetriever = new SequenceFeatureRetriever();

        for(String geneId : geneIdList) {
            homologyRetriever.setGeneId(geneId);
            sequenceFeatureRetriever.setGeneId(geneId);

            homologySaver.execute(homologyRetriever);
            sequenceFeatureSaver.execute(sequenceFeatureRetriever);
        }
    }
}
