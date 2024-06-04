package com.example.reappstart.ui.n2;

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

public class SearchResultViewModel extends AndroidViewModel {
    private static final String TAG = "SearchResultViewModel";
    private databaseManager dbManager;
    private MutableLiveData<List<CookRecipeResponse.RecipeRow>> searchResults = new MutableLiveData<>();

    public SearchResultViewModel(Application application) {
        super(application);
        dbManager = new databaseManager(application);
    }

    public LiveData<List<CookRecipeResponse.RecipeRow>> getSearchResults() {
        return searchResults;
    }

    public void searchRecipes(String query) {
        new SearchRecipesTask(this, dbManager, query).execute();
    }

    private static class SearchRecipesTask extends AsyncTask<Void, Void, List<CookRecipeResponse.RecipeRow>> {
        private WeakReference<SearchResultViewModel> viewModelReference;
        private databaseManager dbManager;
        private String query;

        SearchRecipesTask(SearchResultViewModel viewModel, databaseManager dbManager, String query) {
            this.viewModelReference = new WeakReference<>(viewModel);
            this.dbManager = dbManager;
            this.query = query;
        }

        @Override
        protected List<CookRecipeResponse.RecipeRow> doInBackground(Void... voids) {
            ArrayList<CookRecipeResponse.RecipeRow> result = new ArrayList<>();
            try {
                List<CookRecipeResponse> responses = dbManager.searchItems(query); // 검색 쿼리를 데이터베이스로 전달
                for (CookRecipeResponse response : responses) {
                    result.addAll(response.getCookRcp01().getRowList());
                }
                Log.d(TAG, "Search results loaded: " + result.size());
            } catch (Exception e) {
                Log.e(TAG, "Error loading search results", e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<CookRecipeResponse.RecipeRow> recipeList) {
            SearchResultViewModel viewModel = viewModelReference.get();
            if (viewModel != null && recipeList != null) {
                viewModel.searchResults.setValue(recipeList);
                Log.d(TAG, "Search results set to LiveData: " + recipeList.size());
            }
        }
    }
}
