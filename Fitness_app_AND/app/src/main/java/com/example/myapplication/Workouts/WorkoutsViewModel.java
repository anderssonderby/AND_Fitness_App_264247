package com.example.myapplication.Workouts;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.Model.FitnessRepository;
import com.example.myapplication.Model.Shared.WorkoutModel;

import java.util.Date;
import java.util.List;

public class WorkoutsViewModel extends AndroidViewModel {

    private final FitnessRepository repository;

    public WorkoutsViewModel(Application application) {
        super(application);
        repository = FitnessRepository.getInstance(application);
    }

    public LiveData<List<WorkoutModel>> getWorkouts() {
        return repository.getAllWorkouts();
    }

    public void addWorkout(String description, String musclegroup, Date date) {
        repository.addWorkout(new WorkoutModel(date, musclegroup, description));
    }

}
