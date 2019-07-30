package com.wonokoyo.erpmus.classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class User {
    @PrimaryKey
    private String uid;
    private String username;
    private String password;
    private List<Feature> features;

    public User() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Feature> getFeature() {
        return features;
    }

    public void setFeature(List<Feature> features) {
        this.features = features;
    }
}
