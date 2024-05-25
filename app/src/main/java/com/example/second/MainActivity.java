package com.example.second;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Call<CookRecipeResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        call = retrofit_client.getApiService().stock_api_get("1", "100");

        call.enqueue(new Callback<CookRecipeResponse>() {
            @Override
            public void onResponse(Call<CookRecipeResponse> call, Response<CookRecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CookRecipeResponse cookRecipeResponse = response.body();
                    CookRecipeResponse.CookRcp01 cookRcp01 = cookRecipeResponse.getCookRcp01();
                    if (cookRcp01 != null) {
                        List<CookRecipeResponse.RecipeRow> rowList = cookRcp01.getRowList();
                        if (rowList != null && !rowList.isEmpty()) {
                            CookRecipeResponse.RecipeRow recipeRow = rowList.get(3);
                            String RCP_NM = recipeRow.getRCP_NM();
                            String RCP_WAY2 = recipeRow.getRCP_WAY2();
                            String RCP_PAT2 = recipeRow.getRCP_PAT2();
                            textView.setText("메뉴명:" + RCP_NM + "/조리방법" + RCP_WAY2 + "/요리종류" + RCP_PAT2);
                        } else {
                            textView.setText("데이터가 없습니다 11.");
                        }
                    } else {
                        textView.setText("데이터가 없습니다 22 .");
                    }
                } else {
                    textView.setText("응답이 올바르지 않습니다 33 .");
                }
            }

            @Override
            public void onFailure(Call<CookRecipeResponse> call, Throwable t) {
                textView.setText("실패: " + t.getMessage());
            }
        });
    }
}
