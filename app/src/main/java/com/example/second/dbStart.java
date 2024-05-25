package com.example.second;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dbStart extends AppCompatActivity {

    private databaseManager db;
    private TextView txtOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);

        db = new databaseManager(getApplicationContext());
        txtOutput = findViewById(R.id.txt_output);
        Button btnOutput = findViewById(R.id.btn_output);

        // 데이터를 자동으로 가져와 데이터베이스에 저장
        fetchDataAndStore();

        btnOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 백그라운드 스레드에서 데이터베이스 작업을 수행합니다.
                AsyncTask<Void, Void, ArrayList<CookRecipeResponse>> databaseTask = new AsyncTask<Void, Void, ArrayList<CookRecipeResponse>>() {
                    @Override
                    protected ArrayList<CookRecipeResponse> doInBackground(Void... voids) {
                        return db.getItems(); // 데이터베이스에서 레시피 목록을 가져옵니다.
                    }

                    @Override
                    protected void onPostExecute(ArrayList<CookRecipeResponse> responses) {
                        super.onPostExecute(responses);
                        // 결과를 UI에 반영합니다.
                        StringBuilder output = new StringBuilder();

                        for (CookRecipeResponse response : responses) {
                            CookRecipeResponse.CookRcp01 cookRcp01 = response.getCookRcp01();
                            if (cookRcp01 != null) {
                                List<CookRecipeResponse.RecipeRow> recipeRows = cookRcp01.getRowList();
                                if (recipeRows != null) {
                                    for (CookRecipeResponse.RecipeRow recipeRow : recipeRows) {
                                        output.append("레시피 이름: ").append(recipeRow.getRCP_NM()).append("\n");
                                        output.append("재료: ").append(recipeRow.getRCP_PARTS_DTLS()).append("\n");
                                        output.append("조리 방법: ").append(recipeRow.getRCP_WAY2()).append("\n\n");
                                    }
                                }
                            }
                        }
                        txtOutput.setText(output.toString());
                    }
                };
                databaseTask.execute();
            }
        });
    }

    private void fetchDataAndStore() {
        Retrofit_interface service = retrofit_client.getApiService();
        Call<CookRecipeResponse> call = service.stock_api_get("1", "100"); // 이 매개변수는 상황에 따라 조정하세요.
        call.enqueue(new Callback<CookRecipeResponse>() {
            @Override
            public void onResponse(Call<CookRecipeResponse> call, Response<CookRecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CookRecipeResponse recipeResponse = response.body();
                    // 데이터베이스에 데이터를 삽입합니다.
                    ArrayList<CookRecipeResponse> recipeList = new ArrayList<>();
                    recipeList.add(recipeResponse);
                    db.insertData(recipeList);
                } else {
                    // 네트워크 요청 실패 처리, UI 업데이트는 실시간 상황에 따라 다를 수 있습니다.
                }
            }

            @Override
            public void onFailure(Call<CookRecipeResponse> call, Throwable t) {
                // 네트워크 요청 실패 처리
            }
        });
    }
}
