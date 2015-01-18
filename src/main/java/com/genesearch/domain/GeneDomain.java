package com.genesearch.domain;

import com.genesearch.model.Gene;
import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.edit.HomologueEdit;
import com.genesearch.object.request.SearchGeneRequest;
import com.genesearch.object.response.GeneResponse;
import com.genesearch.repository.GeneHomologueRepository;
import com.genesearch.repository.GeneRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16.01.2015.
 */
@Service
public class GeneDomain {

    @Autowired
    private HomologueDomain homologueDomain;
    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private GeneHomologueRepository geneHomologueRepository;

    public GeneEdit show(String primaryIdentifier) {
        Gene gene = geneRepository.find(primaryIdentifier);
        return gene != null ? GeneEdit.create(gene) : new GeneEdit();
    }

    public GeneEdit showFull(String primaryIdentifier) {
        Gene gene = geneRepository.find(primaryIdentifier);

        List<HomologueEdit> homologueEditList = homologueDomain.find(gene.getId());

        GeneEdit geneEdit = GeneEdit.create(gene);
        geneEdit.setHomologueEditList(homologueEditList);

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
