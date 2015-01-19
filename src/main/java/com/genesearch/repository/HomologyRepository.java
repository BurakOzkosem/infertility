package com.genesearch.repository;

import com.genesearch.model.Homology;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by user on 14.01.2015.
 */
@Repository
public class HomologyRepository extends  ModelRepository<Homology> {


    public Homology find(String primaryIdentifier, String symbol, String organismName, String type, String datasetsName) {
        Criteria c = getSession().createCriteria(getEntityClass(), "hm");
        Conjunction and = new Conjunction();

        safeAddRestrictionEqOrNull(and, "hm.primaryIdentifier", primaryIdentifier);
        safeAddRestrictionEqOrNull(and, "hm.symbol", symbol);
        safeAddRestrictionEqOrNull(and, "hm.organismName", organismName);
        safeAddRestrictionEqOrNull(and, "hm.type", type);
        safeAddRestrictionEqOrNull(and, "hm.datasetsName", datasetsName);

        c.add(and);

        c.setProjection(Projections.countDistinct("hm.id"));
        long total = (Long) c.uniqueResult();

        c.setProjection(null);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Homology> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public List<Homology> find(List<Long> homologyIdList) {
        Criteria c = getSession().createCriteria(getEntityClass(), "hm");
        Conjunction and = new Conjunction();
        and.add(Restrictions.in("hm.id", homologyIdList));
        c.add(and);

        c.setProjection(Projections.countDistinct("hm.id"));
        long total = (Long) c.uniqueResult();

        c.setProjection(null);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return c.list();
    }



}
