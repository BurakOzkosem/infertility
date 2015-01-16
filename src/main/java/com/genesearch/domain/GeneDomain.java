package com.genesearch.domain;

import com.genesearch.model.Gene;
import com.genesearch.object.edit.GeneEdit;
import com.genesearch.repository.GeneHomologueRepository;
import com.genesearch.repository.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
