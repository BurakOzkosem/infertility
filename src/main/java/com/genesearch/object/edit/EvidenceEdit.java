package com.genesearch.object.edit;

import com.genesearch.model.Evidence;

/**
 * Created by user on 07.01.2015.
 */
public class EvidenceEdit extends AbstractEditObject {

    private Long id;

    private PublicationEdit publicationEdit;
    private String baseAnnotationsSubjectBackgroundName;
    private String baseAnnotationsSubjectZygosity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PublicationEdit getPublicationEdit() {
        return publicationEdit;
    }

    public void setPublicationEdit(PublicationEdit publicationEdit) {
        this.publicationEdit = publicationEdit;
    }

    public String getBaseAnnotationsSubjectBackgroundName() {
        return baseAnnotationsSubjectBackgroundName;
    }

    public void setBaseAnnotationsSubjectBackgroundName(String baseAnnotationsSubjectBackgroundName) {
        this.baseAnnotationsSubjectBackgroundName = baseAnnotationsSubjectBackgroundName;
    }

    public String getBaseAnnotationsSubjectZygosity() {
        return baseAnnotationsSubjectZygosity;
    }

    public void setBaseAnnotationsSubjectZygosity(String baseAnnotationsSubjectZygosity) {
        this.baseAnnotationsSubjectZygosity = baseAnnotationsSubjectZygosity;
    }

    public static EvidenceEdit create(Evidence entity) {
        EvidenceEdit editObject = new EvidenceEdit();

        editObject.setId(entity.getId());
        if(entity.getPublication() != null) {
            editObject.setPublicationEdit(PublicationEdit.create(entity.getPublication()));
        }
        editObject.setBaseAnnotationsSubjectBackgroundName(entity.getBaseAnnotationsSubjectBackgroundName());
        editObject.setBaseAnnotationsSubjectZygosity(entity.getBaseAnnotationsSubjectZygosity());

        return editObject;
    }
}
