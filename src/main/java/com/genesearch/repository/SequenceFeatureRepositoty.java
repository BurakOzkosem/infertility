package com.genesearch.repository;

import com.genesearch.model.Gene;
import com.genesearch.model.OntologyAnnotation;
import com.genesearch.model.SequenceFeature;
import com.genesearch.object.edit.OntologyAnnotationEdit;
import com.genesearch.object.edit.SequenceFeatureEdit;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kmorozov on 19.01.2015.
 */
@Repository
public class SequenceFeatureRepositoty extends ModelRepository<SequenceFeature> {

    public List<SequenceFeature> find(Long geneId) {
        Criteria c = getSession().createCriteria(getEntityClass(), "sf");
        c.createAlias("sf.gene", "g", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "g.id", geneId);
        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return c.list();
    }

    public SequenceFeature find(Long geneId, String phenotypeId, String phenotypeName, String evidenceWithText) {
        Criteria c = getSession().createCriteria(getEntityClass(), "sf");
        c.createAlias("sf.gene", "g", JoinType.INNER_JOIN);

        Conjunction and = new Conjunction();
        safeAddRestrictionEq(and, "g.id", geneId);
        safeAddRestrictionEq(and, "phenotypeId", phenotypeId);
        safeAddRestrictionEq(and, "phenotypeName", phenotypeName);
        safeAddRestrictionEq(and, "evidenceWithText", evidenceWithText);
        c.add(and);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<SequenceFeature> result = c.list();

        if(result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

    public void remove(Gene gene, List<SequenceFeatureEdit> sequenceFeatureEditList) {
        Set<Long> remainIdList = new HashSet<Long>();
        Set<SequenceFeature> forDelete = new HashSet<SequenceFeature>();

        for(SequenceFeatureEdit sfEdit : sequenceFeatureEditList) {
            remainIdList.add(sfEdit.getId());
        }

        List<SequenceFeature> sequenceFeatureList =  find(gene.getId());
        for(SequenceFeature  sf : sequenceFeatureList) {
            if(!remainIdList.contains(sf.getId())) {
                forDelete.add(sf);
            }
        }
        delete(forDelete);
    }

//    public SequenceFeature find(Long geneId, String ontologyTermId, String ontologyTermName, String evidenceWithText) {
//        Criteria c = getSession().createCriteria(getEntityClass(), "sf");
//        c.createAlias("sf.gene", "g", JoinType.INNER_JOIN);
//
//        Conjunction and = new Conjunction();
//        safeAddRestrictionEq(and, "g.id", geneId);
//        safeAddRestrictionEq(and, "ontologyTermId", ontologyTermId);
//        safeAddRestrictionEq(and, "ontologyTermName", ontologyTermName);
//        safeAddRestrictionEq(and, "evidenceWithText", evidenceWithText);
//        c.add(and);
//
//        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//        List<SequenceFeature> result = c.list();
//
//        if(result.size() == 0) {
//            return null;
//        }
//
//        return result.get(0);
//    }

}
