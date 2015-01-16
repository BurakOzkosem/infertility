package com.genesearch.repository;

import com.genesearch.model.GeneHomologue;
import com.genesearch.object.edit.GeneEdit;
import com.genesearch.object.edit.HomologueEdit;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmorozov on 16.01.2015.
 */
@Repository
public class GeneHomologueRepository extends ModelRepository<GeneHomologue> {


    public List<GeneHomologue> find(Long geneId, Long homologueId) {
        Criteria c = getSession().createCriteria(getEntityClass(), "gh");
        c.createAlias("gh.gene", "gn", JoinType.INNER_JOIN);
        c.createAlias("gh.homologue", "hm", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();

        safeAddRestrictionEq(and, "gn.id", geneId);
        safeAddRestrictionEq(and, "hm.id", homologueId);

        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<GeneHomologue> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result;
    }


    public void update(GeneEdit geneEdit) {

        List<GeneHomologue> existingList = find(geneEdit.getId(), null);

        outer:
        for(GeneHomologue existing : existingList) {
            for(HomologueEdit he : geneEdit.getHomologueEditList()) {
                if(he.getId().equals(existing.getHomologue().getId())) {
                    continue outer;
                }
            }

        }

    }
}
