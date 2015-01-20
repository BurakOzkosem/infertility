package com.genesearch.webservice;

import org.intermine.metadata.Model;
import org.intermine.pathquery.Constraints;
import org.intermine.pathquery.OrderDirection;
import org.intermine.pathquery.PathQuery;
import org.intermine.webservice.client.core.ServiceFactory;
import org.intermine.webservice.client.services.QueryService;

import java.util.List;

/**
 * Created by user on 19.01.2015.
 */
public class SequenceFeatureRetriever implements WebServiceRetriever {

    private String geneId;

    public SequenceFeatureRetriever() {
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    @Override
    public List<List<Object>> execute() {
        ServiceFactory factory = new ServiceFactory(ROOT);
        Model model = factory.getModel();
        PathQuery query = new PathQuery(model);

// Select the output columns:
        query.addViews("SequenceFeature.primaryIdentifier",
                "SequenceFeature.ontologyAnnotations.ontologyTerm.identifier",
                "SequenceFeature.ontologyAnnotations.ontologyTerm.name",
                "SequenceFeature.ontologyAnnotations.evidence.withText");

        // Add orderby
        query.addOrderBy("SequenceFeature.ontologyAnnotations.ontologyTerm.identifier", OrderDirection.ASC);

        // Filter the results with the following constraints:
        query.addConstraint(Constraints.eq("SequenceFeature.primaryIdentifier", geneId));

        QueryService service = factory.getQueryService();
        return service.getRowsAsLists(query);
    }
}
