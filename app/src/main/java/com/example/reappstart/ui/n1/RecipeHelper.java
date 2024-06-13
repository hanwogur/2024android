package com.example.reappstart.ui.n1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.reappstart.database.databaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeHelper {

    private SQLiteDatabase db;

    public RecipeHelper(Context context) {
        databaseManager dbManager = new databaseManager(context);
        this.db = dbManager.getReadableDatabase();
    }

    public List<Map<String, String>> getRecipeSteps(String rcpSeq) {
        List<Map<String, String>> recipeSteps = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM RECIPE WHERE RCP_SEQ = ?", new String[]{rcpSeq});

            if (cursor.moveToFirst()) {
                for (int j = 1; j <= 20; j++) {
                    String manual = cursor.getString(cursor.getColumnIndexOrThrow("MANUAL" + (j < 10 ? "0" + j : j)));
                    String manualImgUrl = cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG" + (j < 10 ? "0" + j : j)));
                    if (manual != null && !manual.isEmpty()) {
                        Map<String, String> step = new HashMap<>();
                        step.put("manual", manual);
                        step.put("manualImgUrl", manualImgUrl);
                        recipeSteps.add(step);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("RecipeHelper", "Error loading recipe steps", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return recipeSteps;
    }
}
