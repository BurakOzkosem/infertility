package com.genesearch.repository;

import com.genesearch.model.OntologyAnnotation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 06.01.2015.
 */
@Repository
public class OntologyAnnotationRepository extends ModelRepository<OntologyAnnotation> {

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
}
