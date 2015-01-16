package com.genesearch.domain;

import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.edit.MainEdit;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kmorozov on 16.01.2015.
 */
@Service
public class MainGeneEditor {

    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;

    @Autowired
    private GeneRepository geneRepository;

    @Transactional(readOnly = false)
    public MainEdit update(MainEdit editObject) {

        OntologyAnnotationEdit ontologyAnnotationEdit = ontologyAnnotationRepository.update(editObject.getOntologyAnnotationEdit());;
        GeneEdit geneEdit = geneRepository.update(editObject.getGeneEdit());

        return editObject;
    }
}
