package com.wonokoyo.erpmus.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.wonokoyo.erpmus.classes.Feature;
import com.wonokoyo.erpmus.classes.User;

import java.util.List;

@Dao
public interface UserRepository {
    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT features FROM user WHERE uid = (:uid)")
    List<Feature> getUserFeatures(String uid);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}
