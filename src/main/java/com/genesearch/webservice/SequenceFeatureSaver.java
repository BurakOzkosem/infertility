package com.genesearch.webservice;

import com.genesearch.Util;
import com.genesearch.model.Gene;
import com.genesearch.model.SequenceFeature;
import com.genesearch.repository.GeneRepository;
import com.genesearch.repository.SequenceFeatureRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SequenceFeatureSaver implements DbSaver {

    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private SequenceFeatureRepositoty sequenceFeatureRepositoty;

    @Override
    public void execute(WebServiceRetriever retriever) {
        List<List<Object>> result = retriever.execute();

        for (List<Object> row : result) {
            SequenceFeature sequenceFeature = new SequenceFeature();

            sequenceFeature.setPhenotypeId(Util.safeString(row.get(1)));
            sequenceFeature.setPhenotypeName(Util.safeString(row.get(2)));
            sequenceFeature.setEvidenceWithText(Util.safeString(row.get(3)));

            Gene gene = geneRepository.find(Util.safeString(row.get(0)));
            sequenceFeature.setGene(gene);

            SequenceFeature sequenceFeatureFromDb =
                    sequenceFeatureRepositoty.find(gene.getId(), sequenceFeature.getPhenotypeId(), sequenceFeature.getPhenotypeName(), sequenceFeature.getEvidenceWithText());
            if(sequenceFeatureFromDb == null) {
                sequenceFeatureRepositoty.save(sequenceFeature);
            }
        }
    }
}
