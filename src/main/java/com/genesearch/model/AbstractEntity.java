package com.genesearch.model;

/**
 * Created by user on 06.01.2015.
 */
public abstract class AbstractEntity {

    public abstract Long getId();

    public abstract void setId(Long id);

    public boolean equals(Object entity) {

        if(entity == null) {
            return false;
        }

        if(!(entity instanceof AbstractEntity)) {
            return false;
        }

        if(!entity.getClass().equals(this.getClass())) {
            return false;
        }

        AbstractEntity another = (AbstractEntity) entity;

        return this.getId().equals(another.getId());
    }
}
