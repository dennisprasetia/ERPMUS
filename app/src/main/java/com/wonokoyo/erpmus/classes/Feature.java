package com.wonokoyo.erpmus.classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Feature {
    @PrimaryKey
    private int fid;
    private String feature_name;
    private String description;

    public Feature() {

    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
