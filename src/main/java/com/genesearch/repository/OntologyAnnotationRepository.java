package com.genesearch.repository;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.object.request.SearchOntologyAnnotationRequest;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 06.01.2015.
 */
@Repository
public class OntologyAnnotationRepository extends ModelRepository<OntologyAnnotation> {

    @Autowired
    private OntologyTermRepository ontologyTermRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EvidenceRepository evidenceRepository;
    @Autowired
    private GeneRepository geneRepository;

    public OntologyAnnotationEdit show(Long id) {
        return OntologyAnnotationEdit.create(findById(id));
    }

    public OntologyAnnotation find(Long subjectId, Long ontologyTermId, Long evidenceId) {

        Criteria c = getSession().createCriteria(getEntityClass());
        Conjunction and = new Conjunction();

        safeAddRestrictionEq(and, "subject.id", subjectId);
        safeAddRestrictionEq(and, "ontologyTerm.id", ontologyTermId);
        safeAddRestrictionEq(and, "evidence.id", evidenceId);

        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<OntologyAnnotation> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public Page<OntologyAnnotation> search(SearchOntologyAnnotationRequest request) {

        Criteria c = getSession().createCriteria(getEntityClass(), "oa");
        c.createAlias("oa.subject", "s", JoinType.LEFT_OUTER_JOIN);
        c.createAlias("oa.evidence", "e", JoinType.LEFT_OUTER_JOIN);
        c.createAlias("oa.ontologyTerm", "t", JoinType.LEFT_OUTER_JOIN);
        c.createAlias("e.publication", "p", JoinType.LEFT_OUTER_JOIN);


        Conjunction and = new Conjunction();

        safeAddRestrictionEq(and, "oa.id", request.getId());
        safeAddRestrictionEq(and, "t.id", request.getOntologyTermId());
        safeAddRestrictionIlikeAnyWhere(and, "e.baseAnnotationsSubjectBackgroundName", request.getEvidenceBaseAnnotationsSubjectBackgroundName());
        safeAddRestrictionEq(and, "e.baseAnnotationsSubjectZygosity", request.getEvidenceBaseAnnotationsSubjectZygosity());
        safeAddRestrictionIlikeAnyWhere(and, "t.name", request.getOntologyTermName());
        safeAddRestrictionIlikeAnyWhere(and, "t.primaryIdentifier", request.getOntologyTermPrimaryIdentifier());
        safeAddRestrictionEq(and, "p.id", request.getPublicationId());
        safeAddRestrictionIlikeAnyWhere(and, "p.doi", request.getPublicationDoi());
        safeAddRestrictionIlikeAnyWhere(and, "s.primaryIdentifier", request.getSubjectPrimaryIdentifier());
        safeAddRestrictionIlikeAnyWhere(and, "s.symbol", request.getSubjectSymbol());
        safeAddRestrictionIlikeAnyWhere(and, "s.name", request.getSubjectName());
        safeAddRestrictionIlikeAnyWhere(and, "s.dsc", request.getSubjectDsc());
        safeAddRestrictionIlikeAnyWhere(and, "s.chromosomeName", request.getSubjectChromosomeName());

        c.add(and);

        c.setProjection(Projections.countDistinct("oa.id"));
        long total = (Long) c.uniqueResult();

        c.setProjection(null);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (request.getSort() != null) {
            for (Sort.Order order : request.getSort()) {
                String property = order.getProperty();
                if("id".equals(property)) {
                    property = "oa.id";
                } else if("evidenceBaseAnnotationsSubjectBackgroundName".equals(property)) {
                    property = "e.baseAnnotationsSubjectBackgroundName";
                } else if("evidenceBaseAnnotationsSubjectZygosity".equals(property)) {
                    property = "e.baseAnnotationsSubjectZygosity";
                } else if("ontologyTermName".equals(property)) {
                    property = "t.name";
                } else if("ontologyTermPrimaryIdentifier".equals(property)) {
                    property = "t.primaryIdentifier";
                } else if("publicationId".equals(property)) {
                    property = "p.id";
                } else if("publicationDoi".equals(property)) {
                    property = "p.doi";
                } else if("subjectPrimaryIdentifier".equals(property)) {
                    property = "s.primaryIdentifier";
                } else if("subjectSymbol".equals(property)) {
                    property = "s.symbol";
                } else if("subjectName".equals(property)) {
                    property = "s.name";
                } else if("subjectDsc".equals(property)) {
                    property = "s.dsc";
                } else if("subjectChromosomeName".equals(property)) {
                    property = "s.chromosomeName";
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

    public List<OntologyAnnotation> find(String primaryIdentifier) {
        Criteria c = getSession().createCriteria(getEntityClass(), "oa");
        c.createAlias("oa.subject", "s", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "s.primaryIdentifier", primaryIdentifier);
        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return c.list();
    }

    public OntologyAnnotationEdit update(OntologyAnnotationEdit ontologyAnnotationEdit) {
        return null;
    }

    public Set<String> findAllGenes() {
        Set<String> result = new HashSet<String>();

        Criteria c = getSession().createCriteria(getEntityClass(), "oa");

        c.setProjection(Projections.distinct(Projections.property("s2Taxper")));

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return null;
    }
}
