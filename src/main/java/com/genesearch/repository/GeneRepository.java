package com.genesearch.repository;

import com.genesearch.model.Gene;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class GeneRepository extends ModelRepository<Gene> {

    // Used for checking gene uniqueness when saving to database
    public Gene find(String primaryIdentifier, String symbol, String name, String dsc, String chromosome) {
        Criteria c = getSession().createCriteria(getEntityClass(), "gn");

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "gn.primaryIdentifier", primaryIdentifier);
        safeAddRestrictionEq(and, "gn.symbol", symbol);
        safeAddRestrictionEq(and, "gn.name", name);
        safeAddRestrictionEq(and, "gn.dsc", dsc);
        safeAddRestrictionEq(and, "gn.chromosome", chromosome);

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

    // Returns all unique gene primary identifiers
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

}
