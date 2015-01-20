package com.genesearch.repository;

import com.genesearch.model.Phenotype;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmorozov on 19.01.2015.
 */
@Repository
public class PhenotypeRepository extends  ModelRepository<Phenotype>  {


    public Phenotype find(String phenotypeId, String name, String type) {
        Criteria c = getSession().createCriteria(getEntityClass());

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "phenotypeId", phenotypeId);
        safeAddRestrictionEq(and, "name", name);
        safeAddRestrictionEq(and, "type", type);
        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Phenotype> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public List<Phenotype> find(String type) {
        Criteria c = getSession().createCriteria(getEntityClass());

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "type", type);
        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return c.list();
    }
}
