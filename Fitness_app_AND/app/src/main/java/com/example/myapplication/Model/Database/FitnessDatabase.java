package com.example.myapplication.Model.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Model.Shared.WorkoutModel;

@Database(entities = {WorkoutModel.class}, version = 3, exportSchema = false)
public abstract class FitnessDatabase extends RoomDatabase {

    private static FitnessDatabase instance;
    public abstract WorkoutDAO workoutDAO();

    public static synchronized FitnessDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FitnessDatabase.class, "fitness_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
