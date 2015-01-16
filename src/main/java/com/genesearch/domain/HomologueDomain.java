package com.genesearch.domain;

import com.genesearch.model.Homologue;
import com.genesearch.object.edit.HomologueEdit;
import com.genesearch.repository.HomologueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
