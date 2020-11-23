package com.example.myapplication.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Model.Shared.WorkoutModel;

import java.util.List;

@Dao
public interface WorkoutDAO {

    @Insert
    void insert(WorkoutModel workout);

    @Query("SELECT * FROM workout_table ORDER BY _workoutDate DESC")
    LiveData<List<WorkoutModel>> getAllWorkouts();
}