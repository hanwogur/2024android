package com.example.reappstart.database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Retrofit_interface {
    @GET("{start}/{end}")
    Call<CookRecipeResponse> stock_api_get(
            @Path("start") String start,
            @Path("end") String end);
}
