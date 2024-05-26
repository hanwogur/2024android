package com.example.reappstart.ui.n1;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reappstart.database.databaseManager;
import com.example.reappstart.database.CookRecipeResponse;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class N1ViewModel extends AndroidViewModel {
    private static final String TAG = "N1ViewModel";
    private databaseManager dbManager; // 데이터베이스 매니저 객체
    private MutableLiveData<List<CookRecipeResponse.RecipeRow>> recipes = new MutableLiveData<>(); // LiveData로 RecipeRow 목록 관리

    public N1ViewModel(Application application) {
        super(application);
        dbManager = new databaseManager(application); // databaseManager 인스턴스 생성
        loadRecipesAsync(); // 데이터베이스에서 레시피 데이터를 비동기적으로 불러옵니다.
    }

    // 레시피 목록을 비동기적으로 로드하는 메소드
    private void loadRecipesAsync() {
        new LoadRecipesTask(this, dbManager).execute();
    }

    // 레시피 목록을 외부에서 관찰 가능하도록 LiveData로 반환합니다.
    public LiveData<List<CookRecipeResponse.RecipeRow>> getRecipes() {
        return recipes;
    }

    private static class LoadRecipesTask extends AsyncTask<Void, Void, List<CookRecipeResponse.RecipeRow>> {
        private WeakReference<N1ViewModel> viewModelReference;
        private databaseManager dbManager;

        LoadRecipesTask(N1ViewModel viewModel, databaseManager dbManager) {
            this.viewModelReference = new WeakReference<>(viewModel);
            this.dbManager = dbManager;
        }

        @Override
        protected List<CookRecipeResponse.RecipeRow> doInBackground(Void... voids) {
            ArrayList<CookRecipeResponse.RecipeRow> result = new ArrayList<>();
            try {
                List<CookRecipeResponse> responses = dbManager.getItems(); // 백그라운드 스레드에서 데이터베이스 접근
                for (CookRecipeResponse response : responses) {
                    result.addAll(response.getCookRcp01().getRowList()); // 각 CookRecipeResponse에서 RecipeRow 추출
                }
                Log.d(TAG, "Recipes loaded: " + result.size());
            } catch (Exception e) {
                Log.e(TAG, "Error loading recipes", e);
            }
            return result; // 완성된 RecipeRow 리스트 반환
        }

        @Override
        protected void onPostExecute(List<CookRecipeResponse.RecipeRow> recipeList) {
            N1ViewModel viewModel = viewModelReference.get();
            if (viewModel != null && recipeList != null) {
                viewModel.recipes.setValue(recipeList); // 메인 스레드에서 LiveData 업데이트
                Log.d(TAG, "Recipes set to LiveData: " + recipeList.size());
            }
        }
    }
}
