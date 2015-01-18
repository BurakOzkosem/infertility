package com.genesearch.model;

import com.genesearch.object.edit.HomologueEdit;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 14.01.2015.
 */
@Entity
@Table(name="Homologue")
public class Homologue extends AbstractEntity {

    @Id
    @GenericGenerator(name="homologue_generator",strategy="increment")
    @GeneratedValue(generator="homologue_generator")
    @Column(name = "homologue_id")
    private Long id;

    @Column(name="primary_identifier", length = 200)
    private String primaryIdentifier;

    @Column(name="symbol", length = 200)
    private String symbol;

    @Column(name="organism_name", length = 200)
    private String organismName;

    @Column(name="type", length = 200)
    private String type;

    @Column(name="datasets_name", length = 200)
    private String datasetsName;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homologue", cascade = CascadeType.ALL)
//    private Set<GeneHomologue> geneHomologueSet = new HashSet<GeneHomologue>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrganismName() {
        return organismName;
    }

    public void setOrganismName(String organismName) {
        this.organismName = organismName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatasetsName() {
        return datasetsName;
    }

    public void setDatasetsName(String datasetsName) {
        this.datasetsName = datasetsName;
    }

//    public Set<GeneHomologue> getGeneHomologueSet() {
//        return geneHomologueSet;
//    }
//
//    public void setGeneHomologueSet(Set<GeneHomologue> geneHomologueSet) {
//        this.geneHomologueSet = geneHomologueSet;
//    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Homologue homologue = (Homologue) o;
//
//        if (this.datasetsName != null ? !this.datasetsName.equals(homologue.datasetsName) : homologue.datasetsName != null)
//            return false;
//        if (this.geneHomologueSet != null ? !this.geneHomologueSet.equals(homologue.geneHomologueSet) : homologue.geneHomologueSet != null)
//            return false;
//        if (this.organismName != null ? !this.organismName.equals(homologue.organismName) : homologue.organismName != null)
//            return false;
//        if (this.primaryIdentifier != null ? !this.primaryIdentifier.equals(homologue.primaryIdentifier) : homologue.primaryIdentifier != null)
//            return false;
//        if (this.symbol != null ? !this.symbol.equals(homologue.symbol) : homologue.symbol != null) return false;
//        if (this.type != null ? !this.type.equals(homologue.type) : homologue.type != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = this.primaryIdentifier != null ? this.primaryIdentifier.hashCode() : 0;
//        result = 31 * result + (this.symbol != null ? this.symbol.hashCode() : 0);
//        result = 31 * result + (this.organismName != null ? this.organismName.hashCode() : 0);
//        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
//        result = 31 * result + (this.datasetsName != null ? this.datasetsName.hashCode() : 0);
//        result = 31 * result + (this.geneHomologueSet != null ? this.geneHomologueSet.hashCode() : 0);
//        return result;
//    }

    public void update(HomologueEdit ed) {
        this.setPrimaryIdentifier(ed.getPrimaryIdentifier());
        this.setSymbol(ed.getSymbol());
        this.setOrganismName(ed.getOrganismName());
        this.setType(ed.getType());
        this.setDatasetsName(ed.getDatasetsName());
    }
}
