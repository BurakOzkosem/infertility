package com.genesearch.repository;

import com.genesearch.model.Evidence;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 06.01.2015.
 */
@Repository
public class EvidenceRepository extends ModelRepository<Evidence> {

    public Evidence find(String baseAnnoBkgName, String baseAnnoZygocity, Long pubmedId, String doi) {
        Criteria c = getSession().createCriteria(getEntityClass(), "ev");
        c.createAlias("ev.publication", "pub", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();

        safeAddRestrictionEqOrNull(and, "baseAnnotationsSubjectBackgroundName", baseAnnoBkgName);
        safeAddRestrictionEqOrNull(and, "baseAnnotationsSubjectZygosity", baseAnnoZygocity);
        safeAddRestrictionEqOrNull(and, "pubmedId", pubmedId);
        safeAddRestrictionEqOrNull(and, "doi", doi);

        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Evidence> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public Evidence find(Long pubmedId, String baseAnnoBkgName, String baseAnnoZygocity) {

        Criteria c = getSession().createCriteria(getEntityClass(), "ev");
        c.createAlias("ev.publication", "pub", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();

        safeAddRestrictionEqOrNull(and, "pub.id", pubmedId);
        safeAddRestrictionEqOrNull(and, "baseAnnotationsSubjectBackgroundName", baseAnnoBkgName);
        safeAddRestrictionEqOrNull(and, "baseAnnotationsSubjectZygosity", baseAnnoZygocity);

        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Evidence> result = c.list();

        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }


}
