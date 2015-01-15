package com.genesearch.controller;

import com.genesearch.domain.GeneDetailsSaver;
import com.genesearch.domain.MouseMineSaver;
import com.genesearch.model.Gene;
import com.genesearch.model.OntologyTerm;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.object.request.GeneRequest;
import com.genesearch.object.request.SearchOntologyAnnotationRequest;
import com.genesearch.object.response.GeneResponse;
import com.genesearch.object.response.ReferenceResponse;
import com.genesearch.object.response.SearchOntologyAnnotationResponse;
import com.genesearch.repository.GeneRepository;
import com.genesearch.repository.OntologyAnnotationRepository;
import com.genesearch.repository.OntologyTermRepository;
import com.genesearch.webservice.GeneDetailsRetriever;
import com.genesearch.webservice.MouseMineRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 31.12.2014.
 */
@Controller
@RequestMapping("/api")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MouseMineSaver dbSaver;
    @Autowired
    private GeneDetailsSaver geneSaver;


    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;
    @Autowired
    private OntologyTermRepository ontologyTermRepository;


    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/search", method = RequestMethod.POST)
    @ResponseBody
    public Page<SearchOntologyAnnotationResponse> search(@RequestBody SearchOntologyAnnotationRequest request) {
        Page<SearchOntologyAnnotationResponse> responsePage = ontologyAnnotationRepository.search(request);
        return new PageImpl<SearchOntologyAnnotationResponse>(responsePage.getContent(), request, responsePage.getTotalElements());
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/showDetails/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OntologyAnnotationEdit showDetails(@PathVariable Long id) {
        return ontologyAnnotationRepository.show(id);
    }

//    @Transactional(readOnly = false)
//    @RequestMapping(value = "/details/{id}/update", method = RequestMethod.POST)
//    @ResponseBody
//    public OntologyAnnotationEdit updateDetails(@PathVariable Long id, @RequestBody OntologyAnnotationEdit request) {
//        OntologyAnnotation ontologyAnnotation = ontologyAnnotationRepository.findById(request.getId());
//        ontologyAnnotationRepository.update(ontologyAnnotation, request);
//        ontologyAnnotationRepository.save(ontologyAnnotation);
//        return ontologyAnnotationRepository.show(request.getId());
//    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/reference/ontologyterm", method = RequestMethod.GET)
    @ResponseBody
    public List<ReferenceResponse> getOntologyTermReference() {
        List<OntologyTerm> result = new ArrayList<OntologyTerm>();
        result = (List<OntologyTerm>) ontologyTermRepository.getAll();

        List<ReferenceResponse> response = new ArrayList<ReferenceResponse>();
        for(OntologyTerm term : result) {
            response.add(new ReferenceResponse(term.getId(), term.getName()));
        }
        return response;
    }

//=====================================================================================================================
    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/show/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeneResponse showGene(@PathVariable Long id) {
        return geneRepository.show(id);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/gene/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public GeneResponse updateGene(@PathVariable Long id, @RequestBody GeneRequest request) {
        Gene gene = geneRepository.findById(request.getId());
//        gene.update(request);
        geneRepository.save(gene);
        return geneRepository.show(request.getId());
    }

    @RequestMapping(value = "/gene/mm", method = RequestMethod.GET)
    @ResponseBody
    public GeneResponse getFromMM() {
        GeneResponse response = new GeneResponse();

        geneSaver.execute(new GeneDetailsRetriever());

        return response;
    }
}
