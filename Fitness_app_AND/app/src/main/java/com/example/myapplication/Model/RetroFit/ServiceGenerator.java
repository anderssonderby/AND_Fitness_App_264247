package com.example.myapplication.Model.RetroFit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://wger.de").addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static wgerAPI exerciseAPI = retrofit.create(wgerAPI.class);

    public static wgerAPI getExerciseAPI() {
        return exerciseAPI;
    }

}
