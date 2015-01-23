package com.genesearch.domain;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.model.Phenotype;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.repository.OntologyAnnotationRepository;
import com.genesearch.repository.PhenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OntologyAnnotationDomain {

    @Autowired
    private PhenotypeRepository phenotypeRepository;

    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;

    public void update(Gene gene, List<OntologyAnnotationEdit> geneAnnotationList) {
        ontologyAnnotationRepository.remove(gene, geneAnnotationList);

        for(OntologyAnnotationEdit ontologyAnnotationEdit : geneAnnotationList) {
            if(ontologyAnnotationEdit.getId() != null) {
                update(gene, ontologyAnnotationEdit);
            }
            else {
                create(gene, ontologyAnnotationEdit);
            }
        }
    }
    public void create(Gene gene, OntologyAnnotationEdit oaEdit) {
        OntologyAnnotation ontologyAnnotation = new OntologyAnnotation();

        OntologyAnnotation oa = ontologyAnnotationRepository.find(oaEdit.getGeneId(), oaEdit.getOntologyTermId(), oaEdit.getEvidenceBaseAnnotationsSubjectBackgroundName(),
                oaEdit.getEvidenceBaseAnnotationsSubjectZygosity(), oaEdit.getPublicationId(), oaEdit.getPublicationDoi());

        if(oa == null) {
            ontologyAnnotation.setGene(gene);
            if(oaEdit.getOntologyTermId() != null) {
                ontologyAnnotation.setPhenotype(phenotypeRepository.findById(oaEdit.getOntologyTermId()));
            }
            ontologyAnnotation.setBaseAnnotationsSubjectBackgroundName(oaEdit.getEvidenceBaseAnnotationsSubjectBackgroundName());
            ontologyAnnotation.setBaseAnnotationsSubjectZygosity(oaEdit.getEvidenceBaseAnnotationsSubjectZygosity());
            ontologyAnnotation.setPubmedId(oaEdit.getPublicationId());
            ontologyAnnotation.setDoi(oaEdit.getPublicationDoi());

            ontologyAnnotationRepository.save(ontologyAnnotation);
        }
    }

    public void update(Gene gene, OntologyAnnotationEdit oaEdit) {
        OntologyAnnotation oa = ontologyAnnotationRepository.findById(oaEdit.getId());
        Phenotype phenotype = null;
        if(oaEdit.getOntologyTermId() != null) {
            phenotype = phenotypeRepository.findById(oaEdit.getOntologyTermId());
        }
        oa.update(oaEdit, phenotype, gene);
        ontologyAnnotationRepository.save(oa);
    }

}
