package com.genesearch.domain;

import com.genesearch.model.Publication;
import com.genesearch.object.edit.PublicationEdit;
import com.genesearch.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16.01.2015.
 */
@Service
public class PublicationDomain {

    @Autowired
    private PublicationRepository publicationRepository;

    public Publication update(PublicationEdit publicationEdit) {

        Publication publication = publicationRepository.findById(publicationEdit.getId());

        if(publication == null) {
            publication = new Publication();
            publication.setId(publicationEdit.getId());
            publication.setDoi(publicationEdit.getDoi());
        }

        publicationRepository.save(publication);

        return publication;
    }
}
