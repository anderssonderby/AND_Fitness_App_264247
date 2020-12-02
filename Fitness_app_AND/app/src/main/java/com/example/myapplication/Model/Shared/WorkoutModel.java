package com.example.myapplication.Model.Shared;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.myapplication.Model.Database.DateConverter;

import java.util.Date;

@Entity(tableName = "workout_table")
@TypeConverters(DateConverter.class)
public class WorkoutModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date _workoutDate;
    private String muscleGroup;
    private String _description;

    public WorkoutModel(Date _workoutDate, String muscleGroup, String _description) {
        this._workoutDate = _workoutDate;
        this.muscleGroup = muscleGroup;
        this._description = _description;
    }

    public Date get_workoutDate() {
        return _workoutDate;
    }

    public String get_description() {
        return _description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

}
