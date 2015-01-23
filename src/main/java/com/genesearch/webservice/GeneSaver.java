package com.genesearch.webservice;

import com.genesearch.Util;
import com.genesearch.model.*;
import com.genesearch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneSaver implements DbSaver {

    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private PhenotypeRepository phenotypeRepository;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;

    @Override
    public void execute(WebServiceRetriever retriever) {
        List<List<Object>> result = retriever.execute();

        for(List<Object> row : result) {

            OntologyAnnotation ontologyAnnotation = new OntologyAnnotation();
            Gene gene = new Gene();

            Phenotype phenotype = new Phenotype("GENE");

            gene.setPrimaryIdentifier(Util.safeString(row.get(0)));
            gene.setSymbol(Util.safeString(row.get(1)));
            gene.setName(Util.safeString(row.get(2)));
            gene.setDsc(Util.safeString(row.get(4)));
            gene.setChromosome(Util.safeString(row.get(10)));


            phenotype.setPhenotypeId(Util.safeString(row.get(6)));
            phenotype.setName(Util.safeString(row.get(3)));

            Phenotype phenotypeFromDb = phenotypeRepository.find(phenotype.getPhenotypeId(), phenotype.getName(), phenotype.getType());
            if(phenotypeFromDb == null) {
                phenotypeRepository.save(phenotype);
            }
            else {
                phenotype = phenotypeFromDb;
            }

            Gene geneFromDb  = geneRepository.find(gene.getPrimaryIdentifier(), gene.getSymbol(), gene.getName(), gene.getDsc(), gene.getChromosome());
            if(geneFromDb == null) {
                geneRepository.save(gene);
            }
            else {
                gene = geneFromDb;
            }

            ontologyAnnotation.setPhenotype(phenotype);
            ontologyAnnotation.setGene(gene);
            ontologyAnnotation.setBaseAnnotationsSubjectBackgroundName(Util.safeString(row.get(7)));
            ontologyAnnotation.setBaseAnnotationsSubjectZygosity(Util.safeString(row.get(8)));
            ontologyAnnotation.setDoi(Util.safeString(row.get(9)));
            ontologyAnnotation.setPubmedId(Util.safeString(row.get(5)));

            OntologyAnnotation ontologyAnnotationFromDb  = ontologyAnnotationRepository.find(gene.getId(), phenotype.getId(), ontologyAnnotation.getBaseAnnotationsSubjectBackgroundName(),
                    ontologyAnnotation.getBaseAnnotationsSubjectZygosity(), ontologyAnnotation.getPubmedId(), ontologyAnnotation.getDoi());

            if(ontologyAnnotationFromDb == null) {
                ontologyAnnotationRepository.save(ontologyAnnotation);
            }
        }
    }
}
