package com.genesearch.domain;

import com.genesearch.model.Evidence;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.model.OntologyTerm;
import com.genesearch.model.Subject;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.object.edit.OntologyTermEdit;
import com.genesearch.repository.EvidenceRepository;
import com.genesearch.repository.OntologyAnnotationRepository;
import com.genesearch.repository.OntologyTermRepository;
import com.genesearch.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16.01.2015.
 */
@Service
public class OntologyAnnotationDomain {

    @Autowired
    private OntologyTermDomain ontologyTermDomain;
    @Autowired
    private EvidenceDomain evidenceDomain;
    @Autowired
    private SubjectDomain subjectDomain;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;

    public OntologyAnnotationEdit update(OntologyAnnotationEdit ontologyAnnotationEdit) {

        OntologyTerm ontologyTerm = ontologyTermDomain.update(ontologyAnnotationEdit.getOntologyTermEdit());
        Evidence evidence = evidenceDomain.update(ontologyAnnotationEdit.getEvidenceEdit());
        Subject subject = subjectDomain.update(ontologyAnnotationEdit.getSubjectEdit());

        OntologyAnnotation oa = ontologyAnnotationRepository.findById(ontologyAnnotationEdit.getId());
        oa.setOntologyTerm(ontologyTerm);
        oa.setEvidence(evidence);
        oa.setSubject(subject);

        return OntologyAnnotationEdit.create(oa);
    }
}
