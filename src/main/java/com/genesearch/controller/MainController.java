package com.genesearch.controller;

import com.genesearch.domain.GeneDomain;
import com.genesearch.domain.OntologyAnnotationDomain;
import com.genesearch.model.OntologyTerm;
import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.edit.MainEdit;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.object.request.SearchOntologyAnnotationRequest;
import com.genesearch.object.request.SimpleStringRequest;
import com.genesearch.object.response.*;
import com.genesearch.repository.GeneRepository;
import com.genesearch.repository.OntologyAnnotationRepository;
import com.genesearch.repository.OntologyTermRepository;
import com.genesearch.scheduler.ScheduleInformator;
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
    private OntologyAnnotationDomain ontologyAnnotationDomain;
    @Autowired
    private GeneDomain geneDomain;


    @Autowired
    private GeneRepository geneRepository;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;
    @Autowired
    private OntologyTermRepository ontologyTermRepository;

    @Autowired
    private ScheduleInformator scheduleInformator;

    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/short", method = RequestMethod.POST)
    @ResponseBody
    public GeneEdit search(@RequestBody SimpleStringRequest request) {
        return geneRepository.show(request.getValue());
    }

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
    public MainEdit showDetails(@PathVariable Long id) {
        OntologyAnnotationEdit ontologyAnnotationEdit = ontologyAnnotationRepository.show(id);
        GeneEdit geneEdit = geneRepository.show(ontologyAnnotationEdit.getSubjectEdit().getPrimaryIdentifier());
        return new MainEdit(ontologyAnnotationEdit, geneEdit);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/details/update", method = RequestMethod.POST)
    @ResponseBody
    public MainEdit updateDetails(@RequestBody MainEdit request) {
        OntologyAnnotationEdit ontologyAnnotationEdit = ontologyAnnotationDomain.update(request.getOntologyAnnotationEdit());
        GeneEdit geneEdit = geneDomain.update(request.getGeneEdit());
        return new MainEdit(ontologyAnnotationEdit, geneEdit);
    }

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

    @Transactional(readOnly = true)
    @RequestMapping(value = "/downloader/nextStartTime", method = RequestMethod.GET)
    @ResponseBody
    public SimpleDateResponse getNextStartTime() {
        return new SimpleDateResponse(scheduleInformator.getNextStartDate());
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/downloader/status", method = RequestMethod.GET)
    @ResponseBody
    public SimpleStringResponse getDownloadStatus() {
        if(scheduleInformator.getStatus() == null) {
            return new SimpleStringResponse("");
        }
        return new SimpleStringResponse(scheduleInformator.getStatus().name());
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/downloader/startTask", method = RequestMethod.GET)
    @ResponseBody
    public SimpleStringResponse startTask() {

        scheduleInformator.startTask();

        if(scheduleInformator.getStatus() == null) {
            return new SimpleStringResponse("");
        }
        return new SimpleStringResponse(scheduleInformator.getStatus().name());
    }
}
