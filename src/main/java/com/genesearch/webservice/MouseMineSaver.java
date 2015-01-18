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
public class MouseMineSaver implements DbSaver {

    private static final Logger log = LoggerFactory.getLogger(MouseMineSaver.class);

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private OntologyTermRepository ontologyTermRepository;
    @Autowired
    private EvidenceRepository evidenceRepository;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;
    @Autowired
    private HomologueRepository homologueRepository;
    @Autowired
    private GeneRepository geneRepository;

    @Override
    public void execute(WebServiceRetriever retriever) {
        List<List<Object>> result = retriever.execute();

        for(List<Object> row : result) {
            OntologyAnnotation ontologyAnnotation = new OntologyAnnotation();

            Subject subject = new Subject();
            OntologyTerm term = new OntologyTerm();
            Publication publication = new Publication();
            Evidence evidence = new Evidence();

            Gene gene = new Gene();
            Homologue homologue = new Homologue();

            subject.setPrimaryIdentifier(safeString(row.get(0)));
            subject.setSymbol(safeString(row.get(1)));
            subject.setName(safeString(row.get(2)));
            term.setName(safeString(row.get(3)));
            subject.setDsc(safeString(row.get(4)));

            Long pubmedId = null;
            try {
                pubmedId = Long.parseLong(safeString(row.get(5)));
            }
            catch(NumberFormatException ex) {
                    log.warn("Pubmed not found");
            }

            publication.setId(pubmedId);

            term.setPrimaryIdentifier(safeString(row.get(6)));
            evidence.setBaseAnnotationsSubjectBackgroundName(safeString(row.get(7)));
            evidence.setBaseAnnotationsSubjectZygosity(safeString(row.get(8)));
            publication.setDoi(safeString(row.get(9)));
            subject.setChromosomeName(safeString(row.get(10)));

            if(publication.getId() != null) {
                publication = publicationRepository.exists(publication.getId())
                        ? publicationRepository.findById(publication.getId())
                        : publicationRepository.save(publication);

                evidence.setPublication(publication);
            }

            Evidence evidenceFromDb  = evidenceRepository.find(publication.getId(), evidence.getBaseAnnotationsSubjectBackgroundName(), evidence.getBaseAnnotationsSubjectZygosity());
            evidence = evidenceFromDb == null ? evidence : evidenceFromDb;
            if(evidenceFromDb == null) {
                evidenceRepository.save(evidence);
            }
            else {
                evidence = evidenceFromDb;
            }

            Subject subjectFromDb  = subjectRepository.findByPrimaryID(subject.getPrimaryIdentifier());
            if(subjectFromDb == null) {
                subjectRepository.save(subject);
            }
            else {
                subject = subjectFromDb;
            }

            OntologyTerm termFromDb = ontologyTermRepository.findByPrimaryID(term.getPrimaryIdentifier());
            if(termFromDb == null) {
                ontologyTermRepository.save(term);
            }
            else {
                term = termFromDb;
            }

            ontologyAnnotation.setEvidence(evidence);
            ontologyAnnotation.setOntologyTerm(term);
            ontologyAnnotation.setSubject(subject);

            OntologyAnnotation ontologyAnnotationFromDb = ontologyAnnotationRepository.find(subject.getId(), term.getId(), evidence.getId());

            if(ontologyAnnotationFromDb == null) {
                ontologyAnnotationRepository.save(ontologyAnnotation);
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