package com.genesearch.domain;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.model.Phenotype;
import com.genesearch.model.SequenceFeature;
import com.genesearch.object.edit.SequenceFeatureEdit;
import com.genesearch.repository.SequenceFeatureRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20.01.2015.
 */
@Service
public class SequenceFeatureDomain {

    @Autowired
    private SequenceFeatureRepositoty sequenceFeatureRepositoty;

    public List<SequenceFeatureEdit> find(Long geneId) {
        List<SequenceFeatureEdit> result = new ArrayList<SequenceFeatureEdit>();

        List<SequenceFeature> sequenceFeatureList = sequenceFeatureRepositoty.find(geneId);

        for(SequenceFeature sequenceFeature : sequenceFeatureList) {
            result.add(SequenceFeatureEdit.create(sequenceFeature));
        }

        return result;
    }

    public void update(Gene gene, List<SequenceFeatureEdit> sequenceFeatureEditList) {
        sequenceFeatureRepositoty.remove(gene, sequenceFeatureEditList);

        for(SequenceFeatureEdit sequenceFeatureEdit : sequenceFeatureEditList) {
            if(sequenceFeatureEdit.getId() != null) {
                update(gene, sequenceFeatureEdit);
            }
            else {
                create(gene, sequenceFeatureEdit);
            }
        }
    }

    public void update(Gene gene, SequenceFeatureEdit sequenceFeatureEdit) {
        SequenceFeature sf = sequenceFeatureRepositoty.findById(sequenceFeatureEdit.getId());
        sf.update(gene, sequenceFeatureEdit);
        sequenceFeatureRepositoty.save(sf);
    }

    private void create(Gene gene, SequenceFeatureEdit sequenceFeatureEdit) {
        SequenceFeature sequenceFeature = new SequenceFeature();

        SequenceFeature sf = sequenceFeatureRepositoty.find(gene.getId(), sequenceFeatureEdit.getOntologyTermId(),
                sequenceFeatureEdit.getOntologyTermName(), sequenceFeatureEdit.getEvidenceWithText());

        if(sf == null) {
            sequenceFeature.setGene(gene);
            sequenceFeature.setPhenotypeId(sequenceFeatureEdit.getOntologyTermId());
            sequenceFeature.setPhenotypeName(sequenceFeatureEdit.getOntologyTermName());
            sequenceFeature.setEvidenceWithText(sequenceFeatureEdit.getEvidenceWithText());

            sequenceFeatureRepositoty.save(sequenceFeature);
        }
        else {
            // TODO: Similar entity already exists
        }
    }

}
