package com.genesearch.object.edit;

/**
 * Created by user on 20.01.2015.
 */
public class LiteratureEdit {

    public LiteratureEdit() {
    }

    public LiteratureEdit(String publicationId, String publicationDoi) {
        this.publicationId = publicationId;
        this.publicationDoi = publicationDoi;
    }

    private String publicationId;
    private String publicationDoi;

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationDoi() {
        return publicationDoi;
    }

    public void setPublicationDoi(String publicationDoi) {
        this.publicationDoi = publicationDoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LiteratureEdit that = (LiteratureEdit) o;

        if (this.publicationDoi != null ? !this.publicationDoi.equals(that.publicationDoi) : that.publicationDoi != null) return false;
        if (this.publicationId != null ? !this.publicationId.equals(that.publicationId) : that.publicationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.publicationId != null ? this.publicationId.hashCode() : 0;
        result = 31 * result + (this.publicationDoi != null ? this.publicationDoi.hashCode() : 0);
        return result;
    }
}
