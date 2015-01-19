package com.genesearch.domain;

import com.genesearch.model.*;
import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.object.request.SearchOntologyAnnotationRequest;
import com.genesearch.object.response.SearchOntologyAnnotationResponse;
import com.genesearch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private GeneRepository geneRepository;
    @Autowired
    private HomologyDomain homologyDomain;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;
    @Autowired
    private SequenceFeatureRepositoty sequenceFeatureRepositoty;


    public Page<SearchOntologyAnnotationResponse> search(SearchOntologyAnnotationRequest request) {
        List<SearchOntologyAnnotationResponse> responses = new ArrayList<SearchOntologyAnnotationResponse>();

        Page<OntologyAnnotation> searchResult = ontologyAnnotationRepository.search(request);

        for(OntologyAnnotation ontologyAnnotation : searchResult.getContent()) {
            SearchOntologyAnnotationResponse searchOntologyAnnotationResponse = SearchOntologyAnnotationResponse.create(ontologyAnnotation);

            Gene gene = geneRepository.find(ontologyAnnotation.getSubject().getPrimaryIdentifier());
            if(gene != null) {
                GeneEdit geneEdit = GeneEdit.createBrief(gene);
                searchOntologyAnnotationResponse.setGeneEdit(geneEdit);
            }
            responses.add(searchOntologyAnnotationResponse);
        }

        PageImpl<SearchOntologyAnnotationResponse> page = new PageImpl<SearchOntologyAnnotationResponse>(responses, request, searchResult.getTotalElements());

        return page;
    }

    public OntologyAnnotationEdit show(Long id) {
        OntologyAnnotation ontologyAnnotation = ontologyAnnotationRepository.findById(id);
        return OntologyAnnotationEdit.create(ontologyAnnotation);
    }

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
