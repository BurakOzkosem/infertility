package com.genesearch.domain;

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

    public void update(List<SequenceFeatureEdit> sequenceFeatureEditList) {

    }
}
