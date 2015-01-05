package com.genesearch.controller;

import com.genesearch.model.Gene;
import com.genesearch.object.request.GeneRequest;
import com.genesearch.object.request.SearchGeneRequest;
import com.genesearch.object.response.GeneResponse;
import com.genesearch.repository.GeneRepository;
import com.genesearch.webservice.GeneWebServiceClient;
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
public class    MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private GeneRepository geneRepository;

    @Transactional(readOnly = true)
    @RequestMapping(value = "/gene/search", method = RequestMethod.POST)
    @ResponseBody
    public Page<GeneResponse> showGene(@RequestBody SearchGeneRequest request) {
        List<GeneResponse> response = new ArrayList<GeneResponse>();
        response = geneRepository.search(request).getContent();
        return new PageImpl<GeneResponse>(response, request, response.size());
    }

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
        gene.update(request);
        geneRepository.save(gene);
        return geneRepository.show(request.getId());
    }

    @RequestMapping(value = "/gene/mm", method = RequestMethod.GET)
    @ResponseBody
    public GeneResponse getFromMM() {
        GeneResponse response = new GeneResponse();

        GeneWebServiceClient client = new GeneWebServiceClient();
        client.get();

        return response;
    }
}
