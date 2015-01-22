package com.genesearch.repository;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.model.Phenotype;
import com.genesearch.object.request.SearchGeneRequest;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 20.01.2015.
 */
@Repository
public class OntologyAnnotationRepository extends ModelRepository<OntologyAnnotation>{

    @Autowired
    private PhenotypeRepository phenotypeRepository;
    @Autowired
    private GeneRepository geneRepository;

    public Page<OntologyAnnotation> search(SearchGeneRequest request) {
        Criteria c = getSession().createCriteria(getEntityClass(), "oa");
        c.createAlias("oa.phenotype", "ph", JoinType.LEFT_OUTER_JOIN);
        c.createAlias("oa.gene", "gn", JoinType.LEFT_OUTER_JOIN);

        Conjunction and = new Conjunction();

        safeAddRestrictionEq(and, "oa.id", request.getId());
        safeAddRestrictionIlikeAnyWhere(and, "gn.primaryIdentifier", request.getSubjectPrimaryIdentifier());
        safeAddRestrictionIlikeAnyWhere(and, "gn.symbol", request.getSubjectSymbol());
        safeAddRestrictionIlikeAnyWhere(and, "gn.name", request.getSubjectName());
        safeAddRestrictionIlikeAnyWhere(and, "gn.dsc", request.getSubjectDsc());
        safeAddRestrictionIlikeAnyWhere(and, "gn.chromosome", request.getSubjectChromosomeName());

        safeAddRestrictionEq(and, "ph.id", request.getOntologyTermId());
        safeAddRestrictionIlikeAnyWhere(and, "ph.phenotypeId", request.getOntologyTermPrimaryIdentifier());
        safeAddRestrictionIlikeAnyWhere(and, "ph.name", request.getOntologyTermName());

        safeAddRestrictionIlikeAnyWhere(and, "oa.pubmedId", request.getPublicationId());
        safeAddRestrictionIlikeAnyWhere(and, "oa.baseAnnotationsSubjectBackgroundName", request.getEvidenceBaseAnnotationsSubjectBackgroundName());
        safeAddRestrictionEq(and, "oa.baseAnnotationsSubjectZygosity", request.getEvidenceBaseAnnotationsSubjectZygosity());
        safeAddRestrictionIlikeAnyWhere(and, "oa.doi", request.getPublicationDoi());

        c.add(and);

        c.setProjection(Projections.countDistinct("gn.id"));
        long total = (Long) c.uniqueResult();

        c.setProjection(null);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (request.getSort() != null) {
            for (Sort.Order order : request.getSort()) {
                String property = order.getProperty();
                if ("id".equals(property)) {
                    property = "gn.id";
                } else if ("evidenceBaseAnnotationsSubjectBackgroundName".equals(property)) {
                    property = "oa.baseAnnotationsSubjectBackgroundName";
                } else if ("evidenceBaseAnnotationsSubjectZygosity".equals(property)) {
                    property = "oa.baseAnnotationsSubjectZygosity";
                } else if ("ontologyTermName".equals(property)) {
                    property = "ph.name";
                } else if ("ontologyTermPrimaryIdentifier".equals(property)) {
                    property = "ph.phenotypeId";
                } else if ("publicationId".equals(property)) {
                    property = "oa.pubmedId";
                } else if ("publicationDoi".equals(property)) {
                    property = "oa.doi";
                } else if ("subjectPrimaryIdentifier".equals(property)) {
                    property = "gn.primaryIdentifier";
                } else if ("subjectSymbol".equals(property)) {
                    property = "gn.symbol";
                } else if ("subjectName".equals(property)) {
                    property = "gn.name";
                } else if ("subjectDsc".equals(property)) {
                    property = "gn.dsc";
                } else if ("subjectChromosomeName".equals(property)) {
                    property = "gn.chromosomeName";
                }

                Order ord;
                if (order.isAscending()) {
                    ord = Order.asc(property);
                } else {
                    ord = Order.desc(property);
                }
                c.addOrder(ord);
            }
        }

        c.setFirstResult(request.getOffset());
        c.setMaxResults(request.getPageSize());
        return new PageImpl<OntologyAnnotation>(c.list(), request, total);
    }

    public List<OntologyAnnotation> find(Long geneId) {
        Criteria c = getSession().createCriteria(getEntityClass(), "oa");
        c.createAlias("oa.phenotype", "ph", JoinType.LEFT_OUTER_JOIN);
        c.createAlias("oa.gene", "gn", JoinType.LEFT_OUTER_JOIN);

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "gn.id", geneId);

        c.add(and);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return c.list();
    }

    public OntologyAnnotation find(Long geneId, Long phenotypeId, String baseAnnotationsSubjectBackgroundName,
                                   String baseAnnotationsSubjectZygosity, String pubmedId, String doi) {
        Criteria c = getSession().createCriteria(getEntityClass(), "oa");
        c.createAlias("oa.phenotype", "ph", JoinType.LEFT_OUTER_JOIN);
        c.createAlias("oa.gene", "gn", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "gn.id", geneId);

        safeAddRestrictionEqOrNull(and, "ph.id", phenotypeId);
        safeAddRestrictionEqOrNull(and, "oa.pubmedId", pubmedId);
        safeAddRestrictionIlikeAnyWhereOrNull(and, "oa.baseAnnotationsSubjectBackgroundName", baseAnnotationsSubjectBackgroundName);
        safeAddRestrictionEqOrNull(and, "oa.baseAnnotationsSubjectZygosity", baseAnnotationsSubjectZygosity);
        safeAddRestrictionIlikeAnyWhereOrNull(and, "oa.doi", doi);

        c.add(and);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<OntologyAnnotation> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }


}
