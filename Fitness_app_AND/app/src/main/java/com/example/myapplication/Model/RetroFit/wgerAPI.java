package com.example.myapplication.Model.RetroFit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface wgerAPI {

    @GET("api/v2/exercise/")
    Call<ExerciseResponse> getExercises(@Query("language") int languageID);
}
