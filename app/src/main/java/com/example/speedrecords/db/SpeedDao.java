package com.example.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.speedrecords.model.Speed;

@Dao
public interface SpeedDao {


    @Query("SELECT * FROM speed")
    Speed[] showAllData();

    @Insert
    void addSpeed(Speed... speeds);
}
