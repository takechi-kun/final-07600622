package com.example.speedrecords.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "speed")


public class Speed {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public double velocity;
    public double time;
    public double distance;


    public Speed(int id ,double distance ,double time) {
        this.id = id;
        this.time = time;
        this.distance = distance;
        double v = distance / time;
        this.velocity = v * 3600 / 1000;
    }

}
