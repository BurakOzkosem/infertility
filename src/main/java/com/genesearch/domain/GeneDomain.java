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
    private OntologyAnnotationDomain ontologyAnnotationDomain;
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

    public void update(GeneEdit geneEdit) {

        Gene gene = geneRepository.findById(geneEdit.getId());

        gene.update(geneEdit);

        geneRepository.save(gene);

        ontologyAnnotationDomain.update(gene, geneEdit.getGeneAnnotationList());
        sequenceFeatureDomain.update(gene, geneEdit.getSequenceFeatureEditList());
        homologyDomain.update(gene, geneEdit.getHomologyEditList());
    }

    public void create(GeneEdit geneEdit) {

        Gene gene = geneRepository.find(geneEdit.getPrimaryIdentifier(), geneEdit.getSymbol(), geneEdit.getName(), geneEdit.getDsc(), geneEdit.getChromosome());

        if(gene != null) {
            // TODO: Similar entity already exists
            return;
        }

        gene = Gene.create(geneEdit);
        geneRepository.save(gene);

        ontologyAnnotationDomain.update(gene, geneEdit.getGeneAnnotationList());
        sequenceFeatureDomain.update(gene, geneEdit.getSequenceFeatureEditList());
        homologyDomain.update(gene, geneEdit.getHomologyEditList());
    }

}
