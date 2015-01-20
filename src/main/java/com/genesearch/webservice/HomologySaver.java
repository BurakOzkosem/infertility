package com.genesearch.webservice;

import com.genesearch.Util;
import com.genesearch.model.Gene;
import com.genesearch.model.Homology;
import com.genesearch.repository.GeneRepository;
import com.genesearch.repository.HomologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 19.01.2015.
 */
@Service
public class HomologySaver implements DbSaver {

    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private HomologyRepository homologyRepository;

    @Override
    public void execute(WebServiceRetriever retriever) {
        List<List<Object>> result = retriever.execute();

        Gene gene = geneRepository.find(Util.safeString(result.get(0).get(0)));

        gene.setNcbi(Util.safeString(result.get(0).get(7)));        gene.setOrganismName(Util.safeString(result.get(0).get(1)));
        geneRepository.save(gene);

        for (List<Object> row : result) {

            Homology homology = new Homology();

            homology.setPrimaryIdentifier(Util.safeString(row.get(2)));
            homology.setSymbol(Util.safeString(row.get(3)));
            homology.setOrganismName(Util.safeString(row.get(4)));
            homology.setType(Util.safeString(row.get(5)));
            homology.setDatasetsName(Util.safeString(row.get(6)));

            homology.setGene(gene);

            Homology homologyFromDb = homologyRepository.find(homology.getPrimaryIdentifier(), homology.getSymbol(),
                    homology.getOrganismName(), homology.getType(), homology.getDatasetsName(), gene.getId());
            if(homologyFromDb == null) {
                homologyRepository.save(homology);
            }


//            else {
//                homology = homologyFromDb;
//            }



//            GeneHomology gh = new GeneHomology();
//            gh.setGene(gene);
//            gh.setHomology(homology);
//
//            GeneHomology ghFromDb = geneHomologyRepository.findOne(gene.getId(), homology.getId());
//            if(ghFromDb == null) {
//                geneHomologyRepository.save(gh);
//            }
        }
    }
}
