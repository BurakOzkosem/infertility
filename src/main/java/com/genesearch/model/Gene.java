package com.genesearch.model;

import com.genesearch.object.request.GeneRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 02.01.2015.
 */
@Entity
@Table(name="Gene")
public class Gene extends AbstractEntity {

    @Id
    @GenericGenerator(name="gene_generator",strategy="increment")
    @GeneratedValue(generator="gene_generator")
    @Column(name = "gene_id")
    private Long id;

    @Column(name="name")
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(GeneRequest request) {
        this.id = request.getId();
        this.name = request.getName();
    }
}
