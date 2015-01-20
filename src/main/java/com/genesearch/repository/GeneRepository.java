package com.genesearch.repository;

import com.genesearch.model.Gene;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 03.01.2015.
 */
@Repository
public class GeneRepository extends ModelRepository<Gene> {

    public Gene find(String primaryIdentifier, String symbol, String name, String dsc, String chromosome) {
        Criteria c = getSession().createCriteria(getEntityClass(), "gn");

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "gn.primaryIdentifier", primaryIdentifier);
        safeAddRestrictionEq(and, "gn.symbol", symbol);
        safeAddRestrictionEq(and, "gn.name", name);
        safeAddRestrictionEq(and, "gn.dsc", dsc);
        safeAddRestrictionEq(and, "gn.chromosome", chromosome);

//        if(phenotypeId != null) {
//            and.add(Restrictions.eq("ph.id", phenotypeId));
//        }
//        else {
//            and.add(Restrictions.isNull("ph.id"));
//        }
//
//        safeAddRestrictionEqOrNull(and, "gn.baseAnnotationsSubjectBackgroundName", baseAnnotationsSubjectBackgroundName);
//        safeAddRestrictionEqOrNull(and, "gn.baseAnnotationsSubjectZygosity", baseAnnotationsSubjectZygosity);
//        safeAddRestrictionEqOrNull(and, "gn.pubmedId", pubmedId);
//        safeAddRestrictionEqOrNull(and, "gn.doi", doi);

        c.add(and);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Gene> result = c.list();

        if(result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

    public Gene find(Long id) {
        Criteria c = getSession().createCriteria(getEntityClass(), "gn");
        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "gn.id", id);
        c.add(and);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Gene> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public Gene find(String primaryIdentifier) {
        Criteria c = getSession().createCriteria(getEntityClass(), "gn");
        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "gn.primaryIdentifier", primaryIdentifier);
        c.add(and);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Gene> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }


    public Set<String> findAllGenes() {
        Set<String> result = new HashSet<String>();

        Criteria c = getSession().createCriteria(getEntityClass(), "gn");
        c.setProjection(Projections.distinct(Projections.property("gn.primaryIdentifier")));

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<String> genes =  c.list();

        for(String s : genes) {
            result.add(s);
        }
        return result;
    }

//    public Gene find(String primaryIdentifier, String symbol, String organismName, String ncbi) {
//        Criteria c = getSession().createCriteria(getEntityClass(), "gn");
//
//        Conjunction and = new Conjunction();
//
//        safeAddRestrictionEqOrNull(and, "gn.primaryIdentifier", primaryIdentifier);
//        safeAddRestrictionEqOrNull(and, "gn.symbol", symbol);
//        safeAddRestrictionEqOrNull(and, "gn.organismName", organismName);
//        safeAddRestrictionEqOrNull(and, "gn.ncbi", ncbi);
//
//        c.add(and);
//
//        c.setProjection(Projections.countDistinct("gn.id"));
//        long total = (Long) c.uniqueResult();
//
//        c.setProjection(null);
//        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//        List<Gene> result = c.list();
//
//        if(result.size() == 0) {
//            return null;
//        }
//        return result.get(0);
//    }

//    public Page<Gene> search(SearchGeneRequest request) {
//        Criteria c = getSession().createCriteria(getEntityClass(), "gn");
//        c.createAlias("gn.phenotype", "ph", JoinType.LEFT_OUTER_JOIN);
//
//        Conjunction and = new Conjunction();
//
//        safeAddRestrictionEq(and, "gn.id", request.getId());
//        safeAddRestrictionIlikeAnyWhere(and, "gn.primaryIdentifier", request.getSubjectPrimaryIdentifier());
//        safeAddRestrictionIlikeAnyWhere(and, "gn.symbol", request.getSubjectSymbol());
//        safeAddRestrictionIlikeAnyWhere(and, "gn.name", request.getSubjectName());
//        safeAddRestrictionIlikeAnyWhere(and, "gn.dsc", request.getSubjectDsc());
//        safeAddRestrictionIlikeAnyWhere(and, "gn.chromosomeName", request.getSubjectChromosomeName());
//
//        safeAddRestrictionEq(and, "ph.id", request.getOntologyTermId());
//        safeAddRestrictionIlikeAnyWhere(and, "ph.phenotypeId", request.getOntologyTermPrimaryIdentifier());
//        safeAddRestrictionIlikeAnyWhere(and, "ph.name", request.getOntologyTermName());
//
//        safeAddRestrictionEqOrNull(and, "gn.pubmedId", request.getPublicationId());
//        safeAddRestrictionIlikeAnyWhereOrNull(and, "gn.baseAnnotationsSubjectBackgroundName", request.getEvidenceBaseAnnotationsSubjectBackgroundName());
//        safeAddRestrictionEqOrNull(and, "gn.baseAnnotationsSubjectZygosity", request.getEvidenceBaseAnnotationsSubjectZygosity());
//        safeAddRestrictionIlikeAnyWhereOrNull(and, "gn.doi", request.getPublicationDoi());
//
//        c.add(and);
//
//        c.setProjection(Projections.countDistinct("gn.id"));
//        long total = (Long) c.uniqueResult();
//
//        c.setProjection(null);
//        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//        if (request.getSort() != null) {
//            for (Sort.Order order : request.getSort()) {
//                String property = order.getProperty();
//                if("id".equals(property)) {
//                    property = "gn.id";
//                } else if("evidenceBaseAnnotationsSubjectBackgroundName".equals(property)) {
//                    property = "gn.baseAnnotationsSubjectBackgroundName";
//                } else if("evidenceBaseAnnotationsSubjectZygosity".equals(property)) {
//                    property = "gn.baseAnnotationsSubjectZygosity";
//                } else if("ontologyTermName".equals(property)) {
//                    property = "ph.name";
//                } else if("ontologyTermPrimaryIdentifier".equals(property)) {
//                    property = "ph.phenotypeId";
//                } else if("publicationId".equals(property)) {
//                    property = "gn.pubmedId";
//                } else if("publicationDoi".equals(property)) {
//                    property = "gn.doi";
//                } else if("subjectPrimaryIdentifier".equals(property)) {
//                    property = "gn.primaryIdentifier";
//                } else if("subjectSymbol".equals(property)) {
//                    property = "gn.symbol";
//                } else if("subjectName".equals(property)) {
//                    property = "gn.name";
//                } else if("subjectDsc".equals(property)) {
//                    property = "gn.dsc";
//                } else if("subjectChromosomeName".equals(property)) {
//                    property = "gn.chromosomeName";
//                }
//
//                Order ord;
//                if (order.isAscending()) {
//                    ord = Order.asc(property);
//                } else {
//                    ord = Order.desc(property);
//                }
//                c.addOrder(ord);
//            }
//        }
//
//        c.setFirstResult(request.getOffset());
//        c.setMaxResults(request.getPageSize());
//        return new PageImpl<Gene>(c.list(), request, total);
//    }

}
