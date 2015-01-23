package com.genesearch.webservice;

import org.intermine.metadata.Model;
import org.intermine.pathquery.Constraints;
import org.intermine.pathquery.OrderDirection;
import org.intermine.pathquery.PathQuery;
import org.intermine.webservice.client.core.ServiceFactory;
import org.intermine.webservice.client.services.QueryService;

import java.util.List;

public class HomologyRetriever implements WebServiceRetriever{

    private String geneId;

    public HomologyRetriever() {
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
        query.addViews("Gene.primaryIdentifier",
                "Gene.organism.name",
                "Gene.homologues.homologue.primaryIdentifier",
                "Gene.homologues.homologue.symbol",
                "Gene.homologues.homologue.organism.name",
                "Gene.homologues.type",
                "Gene.homologues.dataSets.name",
                "Gene.ncbiGeneNumber");

        // Add orderby
        query.addOrderBy("Gene.primaryIdentifier", OrderDirection.ASC);

        // Filter the results with the following constraints:
        query.addConstraint(Constraints.equalsExactly("Gene.primaryIdentifier", geneId));

        QueryService service = factory.getQueryService();
        return service.getRowsAsLists(query);
    }
}
