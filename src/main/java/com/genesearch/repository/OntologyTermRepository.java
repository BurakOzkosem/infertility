package com.genesearch.repository;

import com.genesearch.model.OntologyTerm;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 06.01.2015.
 */
@Repository
public class OntologyTermRepository extends  ModelRepository<OntologyTerm> {

    public OntologyTerm findByPrimaryID(String primaryIdentifier) {

        Criteria c = getSession().createCriteria(getEntityClass());
        c.add(Restrictions.eq("primaryIdentifier", primaryIdentifier));

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<OntologyTerm> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}
