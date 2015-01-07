package com.genesearch.repository;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Created with IntelliJ IDEA.
 * User: nchudakov
 * Date: 14.11.13
 * Time: 15:34
 */
public abstract class ModelRepository<M> extends GenericModelRepository<M, Long> {

    protected void safeAddRestrictionIlikeAnyWhere(Conjunction and, String fieldName, String fieldValue) {
        if(fieldValue == null ||
                (fieldValue instanceof String && ((String) fieldValue).isEmpty())) {
            return;
        }
        and.add(Restrictions.ilike(fieldName, fieldValue, MatchMode.ANYWHERE));
    }

    protected void safeAddRestrictionEq(Conjunction and, String fieldName, Object fieldValue) {
        if(fieldValue == null ||
                (fieldValue instanceof String && ((String) fieldValue).isEmpty())) {
            return;
        }
        and.add(Restrictions.eq(fieldName, fieldValue));
    }

    protected void safeAddRestrictionGe(Conjunction and, String fieldName, Object fieldValue) {
        if(fieldValue == null ||
                (fieldValue instanceof String && ((String) fieldValue).isEmpty())) {
            return;
        }
        and.add(Restrictions.ge(fieldName, fieldValue));
    }

    protected void safeAddRestrictionLe(Conjunction and, String fieldName, Object fieldValue) {
        if(fieldValue == null ||
                (fieldValue instanceof String && ((String) fieldValue).isEmpty())) {
            return;
        }
        and.add(Restrictions.le(fieldName, fieldValue));
    }
}
