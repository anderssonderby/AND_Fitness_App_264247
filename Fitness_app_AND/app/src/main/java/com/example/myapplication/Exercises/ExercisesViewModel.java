package com.example.myapplication.Exercises;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.example.myapplication.Model.FitnessRepository;
import com.example.myapplication.Model.RetroFit.ExerciseResponse;

import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {

    private final FitnessRepository repository;
    private int languageID;

    public ExercisesViewModel(Application application) {
        super(application);
        repository = FitnessRepository.getInstance(application);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        languageID = Integer.parseInt(sharedPreferences.getString("ex_language", "2"));
    }

    public MutableLiveData<List<ExerciseResponse.Result>> getExercises() {
        return repository.getAllExercises(languageID);
    }
}
