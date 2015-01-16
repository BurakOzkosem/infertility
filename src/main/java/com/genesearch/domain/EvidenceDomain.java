package com.genesearch.domain;

import com.genesearch.model.Evidence;
import com.genesearch.model.OntologyTerm;
import com.genesearch.object.edit.EvidenceEdit;
import com.genesearch.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16.01.2015.
 */
@Service
public class EvidenceDomain {

    @Autowired
    private PublicationDomain publicationDomain;

    @Autowired
    private EvidenceRepository evidenceRepository;

    public Evidence update(EvidenceEdit evidenceEdit) {

        Evidence evidence = evidenceRepository.find(evidenceEdit.getPublicationEdit().getId(),
                evidenceEdit.getBaseAnnotationsSubjectBackgroundName(), evidenceEdit.getBaseAnnotationsSubjectZygosity());

        if(evidence == null) {
            evidence = new Evidence();
            evidence.setBaseAnnotationsSubjectBackgroundName(evidenceEdit.getBaseAnnotationsSubjectBackgroundName());
            evidence.setBaseAnnotationsSubjectZygosity(evidence.getBaseAnnotationsSubjectZygosity());
        }

        evidence.setPublication( publicationDomain.update(evidenceEdit.getPublicationEdit()) );

        evidenceRepository.save(evidence);

        return evidence;
    }
}
