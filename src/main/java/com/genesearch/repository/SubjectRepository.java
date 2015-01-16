package com.genesearch.repository;

import com.genesearch.model.OntologyTerm;
import com.genesearch.model.Subject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 06.01.2015.
 */
@Repository
public class SubjectRepository extends ModelRepository<Subject>{

    public Subject findByPrimaryID(String primaryIdentifier) {

        Criteria c = getSession().createCriteria(getEntityClass());

        Conjunction and = new Conjunction();
        safeAddRestrictionEqOrNull(and, "primaryIdentifier", primaryIdentifier);
        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Subject> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public Subject find(String primaryIdentifier, String symbol, String name, String dsc, String chromosomeName) {
        Criteria c = getSession().createCriteria(getEntityClass());

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "primaryIdentifier", primaryIdentifier);
        safeAddRestrictionEq(and, "symbol", symbol);
        safeAddRestrictionEq(and, "name", name);
        safeAddRestrictionEq(and, "dsc", dsc);
        safeAddRestrictionEq(and, "chromosomeName", chromosomeName);
        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Subject> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}
