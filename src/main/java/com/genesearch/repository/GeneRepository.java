package com.genesearch.repository;

import com.genesearch.model.Gene;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 03.01.2015.
 */
@Repository
public class GeneRepository extends ModelRepository<Gene> {

    public Gene show(Long id) {
        return findById(id);
    }
}
