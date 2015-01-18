package com.genesearch.domain;

import com.genesearch.model.GeneHomologue;
import com.genesearch.model.Homologue;
import com.genesearch.object.edit.HomologueEdit;
import com.genesearch.repository.GeneHomologueRepository;
import com.genesearch.repository.HomologueRepository;
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
public class HomologueDomain {

    @Autowired
    private HomologueRepository homologueRepository;
    @Autowired
    private GeneHomologueRepository geneHomologueRepository;

    public List<HomologueEdit> find(Long geneId) {

        List<HomologueEdit> result = new ArrayList<HomologueEdit>();
        List<Long> homologueIdList = new ArrayList<Long>();

        List<GeneHomologue> geneHomologueList = geneHomologueRepository.find(geneId);
        for(GeneHomologue geneHomologue : geneHomologueList) {
            homologueIdList.add(geneHomologue.getHomologue().getId());
        }

        for(Homologue homologue : homologueRepository.find(homologueIdList)) {
            result.add(HomologueEdit.create(homologue));
        }
        return result;
    }

    public Set<Homologue> update(List<HomologueEdit> homologueEditList) {
        Set<Homologue> result = new HashSet<Homologue>();

        for (HomologueEdit ed : homologueEditList) {

            Homologue forAddEntity = null;

//            Homologue existing = homologueRepository.find(ed.getPrimaryIdentifier(), ed.getSymbol(), ed.getOrganismName(), ed.getType(), ed.getDatasetsName());
            Homologue existing = homologueRepository.findById(ed.getId());

            if (existing == null) {
                Homologue newEntity = new Homologue();
                newEntity.setPrimaryIdentifier(ed.getPrimaryIdentifier());
                newEntity.setSymbol(ed.getSymbol());
                newEntity.setOrganismName(ed.getOrganismName());
                newEntity.setDatasetsName(ed.getDatasetsName());
                newEntity.setType(ed.getType());

                homologueRepository.save(newEntity);

                forAddEntity = newEntity;
            }
            else {
                existing.update(ed);

                forAddEntity = existing;
            }

            result.add(forAddEntity);
        }

        return result;
    }
}
