package com.wonokoyo.erpmus.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.wonokoyo.erpmus.classes.Feature;

import java.util.List;

@Dao
public interface FeatureRepository {
    @Query("SELECT * FROM feature")
    List<Feature> getAllFeature();

    @Insert
    void insertFeature(Feature feature);

    @Delete
    void deleteFeature(Feature feature);
}
