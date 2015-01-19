package com.genesearch.webservice;

import com.genesearch.model.*;
import com.genesearch.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 06.01.2015.
 */
@Service
public class GeneSaver implements DbSaver {

    private static final Logger log = LoggerFactory.getLogger(GeneSaver.class);

    @Autowired
    private EvidenceRepository evidenceRepository;
    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private PhenotypeRepository phenotypeRepository;

    @Override
    public void execute(WebServiceRetriever retriever) {
        List<List<Object>> result = retriever.execute();

        for(List<Object> row : result) {

            Gene gene = new Gene();
            Evidence evidence = new Evidence();
            Phenotype phenotype = new Phenotype();

            gene.setPrimaryIdentifier(safeString(row.get(0)));
            gene.setSymbol(safeString(row.get(1)));
            gene.setName(safeString(row.get(2)));
            gene.setDsc(safeString(row.get(4)));
            gene.setChromosome(safeString(row.get(10)));

            evidence.setBaseAnnotationsSubjectBackgroundName(safeString(row.get(7)));
            evidence.setBaseAnnotationsSubjectZygosity(safeString(row.get(8)));
            evidence.setDoi(safeString(row.get(9)));
            Long pubmedId = null;

            try {
                pubmedId = Long.parseLong(safeString(row.get(5)));
                evidence.setPubmedId(pubmedId);
            }
            catch(NumberFormatException ex) {
                    log.warn("Pubmed not found");
            }

            //phenotype
            phenotype.setPhenotypeId(safeString(row.get(6)));
            phenotype.setName(safeString(row.get(3)));

            Phenotype phenotypeFromDb = phenotypeRepository.find(phenotype.getPhenotypeId(), phenotype.getName());
            if(phenotypeFromDb == null) {
                phenotypeRepository.save(phenotype);
            }
            else {
                phenotype = phenotypeFromDb;
            }

            gene.setPhenotype(phenotype);

            Gene geneFromDb  = geneRepository.find(gene.getPrimaryIdentifier());
            if(geneFromDb == null) {
                geneRepository.save(gene);
            }
            else {
                gene = geneFromDb;
            }

            Evidence evidenceFromDb  = evidenceRepository.find(evidence.getBaseAnnotationsSubjectBackgroundName(), evidence.getBaseAnnotationsSubjectZygosity(), evidence.getPubmedId(), evidence.getDoi());
            evidence = evidenceFromDb == null ? evidence : evidenceFromDb;
            if(evidenceFromDb == null) {
                evidenceRepository.save(evidence);
            }
            else {
                evidence = evidenceFromDb;
            }


        }
    }

    private String safeString(Object object) {
        String result = null;

        if(object == null) {
            return null;
        }

        if(object instanceof String) {
            result = (String) object;
            if(result.trim().equalsIgnoreCase("null")) {
                result = null;
            }
        }
        else {
            result = object.toString();
        }

        return object.toString();
    }

}