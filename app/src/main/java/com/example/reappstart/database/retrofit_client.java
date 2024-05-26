package com.example.reappstart.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit_client {
    private static final String BASE_URL = "https://openapi.foodsafetykorea.go.kr/api/850653a1dd0b4a48b08a/COOKRCP01/json/";

    public static Retrofit_interface getApiService(){
        return getInstance().create(Retrofit_interface.class);
    }

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
