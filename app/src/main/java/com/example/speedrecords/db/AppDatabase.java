package com.example.speedrecords.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.speedrecords.model.Speed;
import com.example.speedrecords.util.AppExecutors;


@Database(entities = {Speed.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";
    private static final String DB_NAME = "speed.db";

    private static AppDatabase sInstance;

    public abstract SpeedDao speedDao();

    public static synchronized AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DB_NAME
            ).addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    insertInitialData(context);
                }
            }).build();
        }
        return sInstance;
    }

    private static void insertInitialData(final Context context) {


        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() { //worker thread
                AppDatabase db = getInstance(context);
               db.speedDao().addSpeed(new Speed(0 , 31 ,0.4),
                       new Speed(0 , 100.0 , 5.0),
                       new Speed(0 , 100 , 4.0));

            }
        });
    }
}