package com.example.myapplication.Model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Database.FitnessDatabase;
import com.example.myapplication.Model.Database.WorkoutDAO;
import com.example.myapplication.Model.RetroFit.ExerciseResponse;
import com.example.myapplication.Model.RetroFit.ServiceGenerator;
import com.example.myapplication.Model.RetroFit.wgerAPI;
import com.example.myapplication.Model.Shared.WorkoutModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FitnessRepository {

    private static FitnessRepository instance;
    private LiveData<List<WorkoutModel>> allWorkouts;
    private MutableLiveData<List<ExerciseResponse.Result>> allExercises;
    private WorkoutDAO workoutDAO;

    private FitnessRepository(Application application) {
        FitnessDatabase database = FitnessDatabase.getInstance(application);
        workoutDAO = database.workoutDAO();
        allWorkouts = workoutDAO.getAllWorkouts();
        allExercises = new MutableLiveData<>();
    }

    public static FitnessRepository getInstance(Application application) {

        if (instance == null) {
            instance = new FitnessRepository(application);
        }

        return instance;
    }

    public LiveData<List<WorkoutModel>> getAllWorkouts() {
        return allWorkouts;
    }

    public void addWorkout(WorkoutModel workout) {
        new addWorkoutAsync(workoutDAO).execute(workout);
    }

    public MutableLiveData<List<ExerciseResponse.Result>> getAllExercises(int langID) {
        requestExercises(langID);
        return allExercises;
    }

    private static class addWorkoutAsync extends AsyncTask<WorkoutModel, Void, Void> {

        private WorkoutDAO dao;

        public addWorkoutAsync(WorkoutDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WorkoutModel... workoutModels) {
            dao.insert(workoutModels[0]);
            return null;
        }
    }

    public void requestExercises(int langID) {

        wgerAPI api = ServiceGenerator.getExerciseAPI();

        Call<ExerciseResponse> call = api.getExercises(langID);

        call.enqueue(new Callback<ExerciseResponse>() {

            @Override
            public void onResponse(Call<ExerciseResponse> call, Response<ExerciseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        allExercises.setValue(response.body().results);
                    }
                }
            }

            @Override
            public void onFailure(Call<ExerciseResponse> call, Throwable t) {
                Log.i("Retrofit", t.toString());
            }
        });
    }
}
