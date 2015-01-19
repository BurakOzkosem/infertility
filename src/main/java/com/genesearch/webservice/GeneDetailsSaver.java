package com.genesearch.webservice;

import com.genesearch.model.*;
import com.genesearch.repository.GeneHomologyRepository;
import com.genesearch.repository.GeneRepository;
import com.genesearch.repository.HomologyRepository;
import com.genesearch.repository.SequenceFeatureRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kmorozov on 15.01.2015.
 */
@Service
public class GeneDetailsSaver implements DbSaver {

    @Autowired
    private HomologyRepository homologyRepository;
    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private GeneHomologyRepository geneHomologyRepository;
    @Autowired
    private SequenceFeatureRepositoty sequenceFeatureRepositoty;

    @Override
    public void execute(WebServiceRetriever retriever) {
        List<List<Object>> result = retriever.execute();

        for(List<Object> row : result) {
            Gene gene = new Gene();
            Homology homology = new Homology();
            SequenceFeature sequenceFeature = new SequenceFeature();

            gene.setPrimaryIdentifier(safeString(row.get(0)));
            gene.setSymbol(safeString(row.get(1)));
            gene.setOrganismName(safeString(row.get(2)));
            homology.setPrimaryIdentifier(safeString(row.get(3)));
            homology.setSymbol(safeString(row.get(4)));
            homology.setOrganismName(safeString(row.get(5)));
            homology.setType(safeString(row.get(6)));
            homology.setDatasetsName(safeString(row.get(7)));
            gene.setNcbi(safeString(row.get(8)));

            sequenceFeature.setOntologyTermId(safeString(row.get(9)));
            sequenceFeature.setOntologyTermName(safeString(row.get(10)));
            sequenceFeature.setEvidenceWithText(safeString(row.get(11)));

            Homology homologyFromDb = homologyRepository.find(homology.getPrimaryIdentifier(), homology.getSymbol(),
                    homology.getOrganismName(), homology.getType(), homology.getDatasetsName());
            if(homologyFromDb == null) {
                homologyRepository.save(homology);
            }
            else {
                homology = homologyFromDb;
            }


            Gene geneFromDb = geneRepository.find(gene.getPrimaryIdentifier(), gene.getSymbol(), gene.getOrganismName(), gene.getNcbi());
            if(geneFromDb == null) {
                geneRepository.save(gene);
            }
            else {
                gene = geneFromDb;
            }

            SequenceFeature sequenceFeatureFromDb = sequenceFeatureRepositoty.find(gene.getId(), sequenceFeature.getOntologyTermId(), sequenceFeature.getOntologyTermName(), sequenceFeature.getEvidenceWithText());
            if(sequenceFeatureFromDb == null) {
                sequenceFeature.setGene(gene);
                sequenceFeatureRepositoty.save(sequenceFeature);
            }

            GeneHomology gh = new GeneHomology();
            gh.setGene(gene);
            gh.setHomology(homology);

            GeneHomology ghFromDb = geneHomologyRepository.findOne(gene.getId(), homology.getId());
            if(ghFromDb == null) {
                geneHomologyRepository.save(gh);
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
