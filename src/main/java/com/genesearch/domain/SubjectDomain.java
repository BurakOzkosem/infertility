package com.genesearch.domain;

import com.genesearch.model.OntologyTerm;
import com.genesearch.model.Subject;
import com.genesearch.object.edit.SubjectEdit;
import com.genesearch.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16.01.2015.
 */
@Service
public class SubjectDomain {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject update(SubjectEdit subjectEdit) {

        Subject subject = subjectRepository.find(subjectEdit.getPrimaryIdentifier(),
                subjectEdit.getSymbol(),
                subjectEdit.getName(),
                subjectEdit.getDsc(),
                subjectEdit.getChromosomeName());

        if(subject == null) {
            subject = new Subject();
            subject.setPrimaryIdentifier(subjectEdit.getPrimaryIdentifier());
            subject.setSymbol(subjectEdit.getSymbol());
            subject.setName(subjectEdit.getName());
            subject.setDsc(subjectEdit.getDsc());
            subject.setChromosomeName(subjectEdit.getChromosomeName());
        }

        subjectRepository.save(subject);

        return subject;
    }
}
