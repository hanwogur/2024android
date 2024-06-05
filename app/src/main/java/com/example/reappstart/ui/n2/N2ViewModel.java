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

public class N2ViewModel extends AndroidViewModel {
    private static final String TAG = "N2ViewModel";
    private databaseManager dbManager;
    private MutableLiveData<List<CookRecipeResponse.RecipeRow>> recipes = new MutableLiveData<>();

    public N2ViewModel(Application application) {
        super(application);
        dbManager = new databaseManager(application);
        loadRecipesAsync();
    }

    private void loadRecipesAsync() {
        new LoadRecipesTask(this, dbManager).execute();
    }

    public LiveData<List<CookRecipeResponse.RecipeRow>> getRecipes() {
        return recipes;
    }

    private static class LoadRecipesTask extends AsyncTask<Void, Void, List<CookRecipeResponse.RecipeRow>> {
        private WeakReference<N2ViewModel> viewModelReference;
        private databaseManager dbManager;

        LoadRecipesTask(N2ViewModel viewModel, databaseManager dbManager) {
            this.viewModelReference = new WeakReference<>(viewModel);
            this.dbManager = dbManager;
        }

        @Override
        protected List<CookRecipeResponse.RecipeRow> doInBackground(Void... voids) {
            ArrayList<CookRecipeResponse.RecipeRow> result = new ArrayList<>();
            try {
                List<CookRecipeResponse> responses = dbManager.getItems();
                for (CookRecipeResponse response : responses) {
                    result.addAll(response.getCookRcp01().getRowList());
                }
                Log.d(TAG, "Recipes loaded: " + result.size());
            } catch (Exception e) {
                Log.e(TAG, "Error loading recipes", e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<CookRecipeResponse.RecipeRow> recipeList) {
            N2ViewModel viewModel = viewModelReference.get();
            if (viewModel != null && recipeList != null) {
                viewModel.recipes.setValue(recipeList);
                Log.d(TAG, "Recipes set to LiveData: " + recipeList.size());
            }
        }
    }
}
