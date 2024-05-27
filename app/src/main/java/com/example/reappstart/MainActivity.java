package com.example.reappstart;

import com.example.reappstart.database.CookRecipeResponse;
import com.example.reappstart.database.databaseManager;
import com.example.reappstart.database.retrofit_client;
import com.example.reappstart.database.Retrofit_interface;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.reappstart.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private databaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_n1, R.id.navigation_n2, R.id.navigation_n3, R.id.navigation_n4, R.id.navigation_n5)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        db = new databaseManager(getApplicationContext());

        fetchDataAndStore();
    }

    private void fetchDataAndStore() {
        Retrofit_interface service = retrofit_client.getApiService();
        Call<CookRecipeResponse> call = service.stock_api_get("1", "100");
        call.enqueue(new Callback<CookRecipeResponse>() {
            @Override
            public void onResponse(Call<CookRecipeResponse> call, Response<CookRecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CookRecipeResponse recipeResponse = response.body();
                    //데이터 베이스에 데이터를 삽입
                    ArrayList<CookRecipeResponse> recipeList = new ArrayList<>();
                    recipeList.add(recipeResponse);
                    db.insertData(recipeList);
                } else {
                    // 네트워크 요청 실패 처리, UI 업데이트는 실시간 상황에 따라 다를 수 있다.
                }
            }

            @Override
            public void onFailure(Call<CookRecipeResponse> call, Throwable t) {
                // 네트워크 요청 실패 처리
            }
        });
    }

}