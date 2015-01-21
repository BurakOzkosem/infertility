package com.genesearch.object.edit;

/**
 * Created by user on 20.01.2015.
 */
public class GenotypeEdit {

    public GenotypeEdit() {
    }

    public GenotypeEdit(String background, String zygosity) {
        this.background = background;
        this.zygosity = zygosity;
    }

    private String background;
    private String zygosity;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getZygosity() {
        return zygosity;
    }

    public void setZygosity(String zygosity) {
        this.zygosity = zygosity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenotypeEdit that = (GenotypeEdit) o;

        if (this.background != null ? !this.background.equals(that.background) : that.background != null) return false;
        if (this.zygosity != null ? !this.zygosity.equals(that.zygosity) : that.zygosity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.background != null ? this.background.hashCode() : 0;
        result = 31 * result + (this.zygosity != null ? this.zygosity.hashCode() : 0);
        return result;
    }
}
