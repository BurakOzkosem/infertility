package com.genesearch.domain;

import com.genesearch.model.OntologyTerm;
import com.genesearch.object.edit.OntologyTermEdit;
import com.genesearch.repository.OntologyTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16.01.2015.
 */
@Service
public class OntologyTermDomain {

    @Autowired
    private OntologyTermRepository ontologyTermRepository;

    public OntologyTerm update(OntologyTermEdit ontologyTermEdit) {

        OntologyTerm term = ontologyTermRepository.findByPrimaryID(ontologyTermEdit.getPrimaryIdentifier());

        if(term == null) {
            term = new OntologyTerm();
            term.setPrimaryIdentifier(ontologyTermEdit.getPrimaryIdentifier());
            term.setName(ontologyTermEdit.getName());
        }

        ontologyTermRepository.save(term);

        return term;
    }
}
