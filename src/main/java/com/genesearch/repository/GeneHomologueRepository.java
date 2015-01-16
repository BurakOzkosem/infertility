package com.genesearch.repository;

import com.genesearch.model.Gene;
import com.genesearch.model.GeneHomologue;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmorozov on 16.01.2015.
 */
@Repository
public class GeneHomologueRepository extends ModelRepository<GeneHomologue> {


    public GeneHomologue find(Long geneId, Long homologueId) {
        Criteria c = getSession().createCriteria(getEntityClass(), "gh");
        c.createAlias("gh.gene", "gn", JoinType.INNER_JOIN);
        c.createAlias("gh.homologue", "hm", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();

        safeAddRestrictionEqOrNull(and, "gn.id", geneId);
        safeAddRestrictionEqOrNull(and, "hm.id", homologueId);

        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<GeneHomologue> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}
