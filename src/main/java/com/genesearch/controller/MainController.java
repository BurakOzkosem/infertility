package com.genesearch.controller;

import com.genesearch.domain.GeneDomain;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.model.Phenotype;
import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.request.SearchGeneRequest;
import com.genesearch.object.response.ReferenceResponse;
import com.genesearch.object.response.SearchGeneResponse;
import com.genesearch.object.response.SimpleDateResponse;
import com.genesearch.object.response.SimpleStringResponse;
import com.genesearch.repository.OntologyAnnotationRepository;
import com.genesearch.repository.PhenotypeRepository;
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
    private GeneDomain geneDomain;
    @Autowired
    private PhenotypeRepository phenotypeRepository;
    @Autowired
    private OntologyAnnotationRepository ontologyAnnotationRepository;

    @Autowired
    private ScheduleInformator scheduleInformator;

    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/search", method = RequestMethod.POST)
    @ResponseBody
    public Page<SearchGeneResponse> search(@RequestBody SearchGeneRequest request) {
        Page<OntologyAnnotation> searchResult = ontologyAnnotationRepository.search(request);

        List<SearchGeneResponse> result = new ArrayList<SearchGeneResponse>();
        for(OntologyAnnotation ontologyAnnotation : searchResult.getContent()) {
            result.add(SearchGeneResponse.create(ontologyAnnotation));
        }

        return new PageImpl<SearchGeneResponse>(result, request, searchResult.getTotalElements());
    }

//    @Transactional(readOnly = true)
//    @RequestMapping(value = "/gene/short", method = RequestMethod.POST)
//    @ResponseBody
//    public GeneEdit search(@RequestBody SimpleStringRequest request) {
//        return geneDomain.show(request.getValue());
//    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/showDetails/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeneEdit showDetails(@PathVariable Long id) {
        return geneDomain.showFull(id);
    }

//    @Transactional(readOnly = false)
//    @RequestMapping(value = "/details/update", method = RequestMethod.POST)
//    @ResponseBody
//    public MainEdit updateDetails(@RequestBody MainEdit request) {
//        OntologyAnnotationEdit ontologyAnnotationEdit = ontologyAnnotationDomain.update(request.getOntologyAnnotationEdit());
//        GeneEdit geneEdit = geneDomain.update(request.getGeneEdit());
//        return new MainEdit(ontologyAnnotationEdit, geneEdit);
//    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/reference/ontologyterm", method = RequestMethod.GET)
    @ResponseBody
    public List<ReferenceResponse> getOntologyTermReference() {
        List<Phenotype> result = phenotypeRepository.find("GENE");

        List<ReferenceResponse> response = new ArrayList<ReferenceResponse>();
        for(Phenotype phenotype : result) {
            response.add(new ReferenceResponse(phenotype.getId(), phenotype.getName()));
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
