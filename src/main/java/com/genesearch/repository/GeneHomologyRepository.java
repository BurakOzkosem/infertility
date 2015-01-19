package com.genesearch.repository;

import com.genesearch.model.GeneHomology;
import com.genesearch.object.edit.GeneEdit;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmorozov on 16.01.2015.
 */
@Repository
public class GeneHomologyRepository extends ModelRepository<GeneHomology> {


    public List<GeneHomology> find(Long geneId) {
        Criteria c = getSession().createCriteria(getEntityClass(), "gh");
        c.createAlias("gh.gene", "gn", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();

        safeAddRestrictionEq(and, "gn.id", geneId);

        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<GeneHomology> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result;
    }

    public GeneHomology findOne(Long geneId, Long homologueId) {
        if(geneId == null || homologueId == null) {
            throw new IllegalArgumentException();
        }

        Criteria c = getSession().createCriteria(getEntityClass(), "gh");
        c.createAlias("gh.gene", "gn", JoinType.INNER_JOIN);
        c.createAlias("gh.homology", "hm", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();

        safeAddRestrictionEq(and, "gn.id", geneId);
        safeAddRestrictionEq(and, "hm.id", homologueId);

        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<GeneHomology> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public void update(GeneEdit geneEdit) {


    }
}
