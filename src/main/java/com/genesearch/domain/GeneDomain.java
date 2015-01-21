package com.genesearch.domain;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.object.edit.*;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.repository.GeneRepository;
import com.genesearch.repository.OntologyAnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SequenceFeatureDomain sequenceFeatureDomain;
    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;

    public GeneEdit showFull(Long id) {
        Gene gene = geneRepository.find(id);

        Long geneId = gene.getId();

        List<OntologyAnnotationEdit> ontologyAnnotationEditList = new ArrayList<OntologyAnnotationEdit>();

        List<OntologyAnnotation> ontologyAnnotationList = ontologyAnnotationRepository.find(geneId);

        Set<GenotypeEdit> genotypeEditList = new HashSet<GenotypeEdit>();
        Set<LiteratureEdit> literatureEditList = new HashSet<LiteratureEdit>();

        for(OntologyAnnotation g : ontologyAnnotationList) {
            genotypeEditList.add(new GenotypeEdit(g.getBaseAnnotationsSubjectBackgroundName(), g.getBaseAnnotationsSubjectZygosity()));
            literatureEditList.add(new LiteratureEdit(g.getPubmedId(), g.getDoi()));
            ontologyAnnotationEditList.add(OntologyAnnotationEdit.create(g));
        }

        List<HomologyEdit> homologyEditList = homologyDomain.find(geneId);
        List<SequenceFeatureEdit> sequenceFeatureList = sequenceFeatureDomain.find(geneId);

        GeneEdit geneEdit = GeneEdit.create(gene);
        geneEdit.setHomologyEditList(homologyEditList);
        geneEdit.setSequenceFeatureEditList(sequenceFeatureList);
        geneEdit.setGenotypeEditList(genotypeEditList);
        geneEdit.setLiteratureEditList(literatureEditList);

        geneEdit.setGeneAnnotationList(ontologyAnnotationEditList);

        return geneEdit;
    }

    public GeneEdit update(GeneEdit geneEdit) {

        Gene gene = geneRepository.findById(geneEdit.getId());

        gene.update(geneEdit);

        geneRepository.save(gene);

        ontologyAnnotationRepository.update(geneEdit.getGeneAnnotationList());
        sequenceFeatureDomain.update(geneEdit.getSequenceFeatureEditList());
        homologyDomain.update(geneEdit.getHomologyEditList());
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

    public void create(GeneEdit request) {
    }

}
