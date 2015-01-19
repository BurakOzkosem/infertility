package com.genesearch.domain;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.model.Publication;
import com.genesearch.model.SequenceFeature;
import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.edit.HomologyEdit;
import com.genesearch.object.request.SearchGeneRequest;
import com.genesearch.object.response.GeneResponse;
import com.genesearch.repository.GeneHomologueRepository;
import com.genesearch.repository.GeneRepository;
import com.genesearch.repository.OntologyAnnotationRepository;
import com.genesearch.repository.SequenceFeatureRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 16.01.2015.
 */
@Service
public class GeneDomain {

    @Autowired
    private HomologyDomain homologyDomain;
    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;
    @Autowired
    private SequenceFeatureRepositoty sequenceFeatureRepositoty;

    public GeneEdit show(String primaryIdentifier) {
        Gene gene = geneRepository.find(primaryIdentifier);
        GeneEdit geneEdit = GeneEdit.createBrief(gene);
        return gene != null ? geneEdit : new GeneEdit();
    }

    public GeneEdit showFull(String primaryIdentifier) {
        Gene gene = geneRepository.find(primaryIdentifier);
        List<OntologyAnnotation> ontologyAnnotationList = ontologyAnnotationRepository.find(gene.getPrimaryIdentifier());
        Set<String> phenoTypes = new HashSet<String>();
        List<Publication> publicationList = new ArrayList<Publication>();
        for(OntologyAnnotation ontologyAnnotation : ontologyAnnotationList) {
            if(ontologyAnnotation.getOntologyTerm() != null) {
                phenoTypes.add(ontologyAnnotation.getOntologyTerm().getName());
            }
            if(ontologyAnnotation.getEvidence() != null) {
                publicationList.add(ontologyAnnotation.getEvidence().getPublication());
            }
        }

        List<HomologyEdit> homologyEditList = homologyDomain.find(gene.getId());
        List<SequenceFeature> sequenceFeatureList = sequenceFeatureRepositoty.find(gene.getId());

        GeneEdit geneEdit = GeneEdit.create(gene, sequenceFeatureList, phenoTypes, publicationList);
        geneEdit.setHomologyEditList(homologyEditList);

        return geneEdit;
    }

    public GeneResponse show(Long id) {
        return GeneResponse.create(geneRepository.findById(id));
    }

    public Page<GeneResponse> search(SearchGeneRequest request) {

        Page<Gene> searchResult = geneRepository.search(request);

        List<GeneResponse> responses = new ArrayList<GeneResponse>();
        for(Gene gene : searchResult.getContent()) {
            responses.add(GeneResponse.create(gene));
        }

        return new PageImpl<GeneResponse>(responses, request, searchResult.getTotalElements());
    }

    public GeneEdit update(GeneEdit geneEdit) {

        Gene gene = geneRepository.findById(geneEdit.getId());

        gene.update(geneEdit);


/**
 * The code below needed in case of updating homologues of gene
 *
 * */

//        Set<Homologue> newHomologues = homologueDomain.update(geneEdit.getHomologueEditList());
//
//        Iterator<GeneHomologue> it = gene.getGeneHomologueSet().iterator();
//
//        while(it.hasNext()) {
//            GeneHomologue existing = it.next();
//            if(!newHomologues.contains(existing.getHomologue())) {
//                it.remove();
//            }
//        }
//
//        for(Homologue hm : newHomologues) {
//            GeneHomologue existing = geneHomologueRepository.findOne(gene.getId(), hm.getId());
//            gene.getGeneHomologueSet().add(existing != null ? existing : new GeneHomologue(gene, hm));
//        }

        geneRepository.save(gene);

        return geneEdit;
    }
}
