package com.example.reappstart.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.reappstart.data.CookRecipeResponse;

import java.util.ArrayList;

public class databaseManager extends SQLiteOpenHelper {

    public databaseManager(Context context) {
        super(context, "ny.db", null, 1); // 기본 생성자로 이름, CursorFactory, 버전을 설정합니다.
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RECIPE(" +
                "RCP_SEQ TEXT PRIMARY KEY NOT NULL," +
                "RCP_NM TEXT," +
                "RCP_WAY2 TEXT," +
                "RCP_PAT2 TEXT," +
                "INFO_WGT TEXT," +
                "INFO_ENG TEXT," +
                "INFO_CAR TEXT," +
                "INFO_PRO TEXT," +
                "INFO_FAT TEXT," +
                "INFO_NA TEXT," +
                "HASH_TAG TEXT," +
                "ATT_FILE_NO_MAIN TEXT," +
                "ATT_FILE_NO_MK TEXT," +
                "RCP_PARTS_DTLS TEXT," +
                "MANUAL01 TEXT," +
                "MANUAL_IMG01 TEXT," +
                "MANUAL02 TEXT," +
                "MANUAL_IMG02 TEXT," +
                "MANUAL03 TEXT," +
                "MANUAL_IMG03 TEXT," +
                "MANUAL04 TEXT," +
                "MANUAL_IMG04 TEXT," +
                "MANUAL05 TEXT," +
                "MANUAL_IMG05 TEXT," +
                "MANUAL06 TEXT," +
                "MANUAL_IMG06 TEXT," +
                "MANUAL07 TEXT," +
                "MANUAL_IMG07 TEXT," +
                "MANUAL08 TEXT," +
                "MANUAL_IMG08 TEXT," +
                "MANUAL09 TEXT," +
                "MANUAL_IMG09 TEXT," +
                "MANUAL10 TEXT," +
                "MANUAL_IMG10 TEXT," +
                "MANUAL11 TEXT," +
                "MANUAL_IMG11 TEXT," +
                "MANUAL12 TEXT," +
                "MANUAL_IMG12 TEXT," +
                "MANUAL13 TEXT," +
                "MANUAL_IMG13 TEXT," +
                "MANUAL14 TEXT," +
                "MANUAL_IMG14 TEXT," +
                "MANUAL15 TEXT," +
                "MANUAL_IMG15 TEXT," +
                "MANUAL16 TEXT," +
                "MANUAL_IMG16 TEXT," +
                "MANUAL17 TEXT," +
                "MANUAL_IMG17 TEXT," +
                "MANUAL18 TEXT," +
                "MANUAL_IMG18 TEXT," +
                "MANUAL19 TEXT," +
                "MANUAL_IMG19 TEXT," +
                "MANUAL20 TEXT," +
                "MANUAL_IMG20 TEXT," +
                "RCP_NA_TIP TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if database schema needs to be changed in future versions
    }

    public void insertData(ArrayList<CookRecipeResponse> list) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (CookRecipeResponse response : list) {
                for (CookRecipeResponse.RecipeRow row : response.getCookRcp01().getRowList()) {
                    values.clear(); // Clear ContentValues for new row
                    values.put("RCP_SEQ", row.getRCP_SEQ());
                    values.put("RCP_NM", row.getRCP_NM());
                    values.put("RCP_WAY2", row.getRCP_WAY2());
                    values.put("RCP_PAT2", row.getRCP_PAT2());
                    values.put("INFO_WGT", row.getINFO_WGT());
                    values.put("INFO_ENG", row.getINFO_ENG());
                    values.put("INFO_CAR", row.getINFO_CAR());
                    values.put("INFO_PRO", row.getINFO_PRO());
                    values.put("INFO_FAT", row.getINFO_FAT());
                    values.put("INFO_NA", row.getINFO_NA());
                    values.put("HASH_TAG", row.getHASH_TAG());
                    values.put("ATT_FILE_NO_MAIN", row.getATT_FILE_NO_MAIN());
                    values.put("ATT_FILE_NO_MK", row.getATT_FILE_NO_MK());
                    values.put("RCP_PARTS_DTLS", row.getRCP_PARTS_DTLS());
                    values.put("MANUAL01", row.getMANUAL01());
                    values.put("MANUAL_IMG01", row.getMANUAL_IMG01());
                    values.put("MANUAL02", row.getMANUAL02());
                    values.put("MANUAL_IMG02", row.getMANUAL_IMG02());
                    values.put("MANUAL03", row.getMANUAL03());
                    values.put("MANUAL_IMG03", row.getMANUAL_IMG03());
                    values.put("MANUAL04", row.getMANUAL04());
                    values.put("MANUAL_IMG04", row.getMANUAL_IMG04());
                    values.put("MANUAL05", row.getMANUAL05());
                    values.put("MANUAL_IMG05", row.getMANUAL_IMG05());
                    values.put("MANUAL06", row.getMANUAL06());
                    values.put("MANUAL_IMG06", row.getMANUAL_IMG06());
                    values.put("MANUAL07", row.getMANUAL07());
                    values.put("MANUAL_IMG07", row.getMANUAL_IMG07());
                    values.put("MANUAL08", row.getMANUAL08());
                    values.put("MANUAL_IMG08", row.getMANUAL_IMG08());
                    values.put("MANUAL09", row.getMANUAL09());
                    values.put("MANUAL_IMG09", row.getMANUAL_IMG09());
                    values.put("MANUAL10", row.getMANUAL10());
                    values.put("MANUAL_IMG10", row.getMANUAL_IMG10());
                    values.put("MANUAL11", row.getMANUAL11());
                    values.put("MANUAL_IMG11", row.getMANUAL_IMG11());
                    values.put("MANUAL12", row.getMANUAL12());
                    values.put("MANUAL_IMG12", row.getMANUAL_IMG12());
                    values.put("MANUAL13", row.getMANUAL13());
                    values.put("MANUAL_IMG13", row.getMANUAL_IMG13());
                    values.put("MANUAL14", row.getMANUAL14());
                    values.put("MANUAL_IMG14", row.getMANUAL_IMG14());
                    values.put("MANUAL15", row.getMANUAL15());
                    values.put("MANUAL_IMG15", row.getMANUAL_IMG15());
                    values.put("MANUAL16", row.getMANUAL16());
                    values.put("MANUAL_IMG16", row.getMANUAL_IMG16());
                    values.put("MANUAL17", row.getMANUAL17());
                    values.put("MANUAL_IMG17", row.getMANUAL_IMG17());
                    values.put("MANUAL18", row.getMANUAL18());
                    values.put("MANUAL_IMG18", row.getMANUAL_IMG18());
                    values.put("MANUAL19", row.getMANUAL19());
                    values.put("MANUAL_IMG19", row.getMANUAL_IMG19());
                    values.put("MANUAL20", row.getMANUAL20());
                    values.put("MANUAL_IMG20", row.getMANUAL_IMG20());
                    values.put("RCP_NA_TIP", row.getRCP_NA_TIP());
                    db.insert("RECIPE", null, values);
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<CookRecipeResponse> getItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPE", null);
        ArrayList<CookRecipeResponse> list = new ArrayList<>();

        try {
            if (cursor.moveToFirst()) {
                do {
                    CookRecipeResponse response = new CookRecipeResponse();
                    response.getCookRcp01().getRowList().add(setRecipeRow(cursor));
                    list.add(response);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            db.close();
        }

        return list;
    }

    private CookRecipeResponse.RecipeRow setRecipeRow(Cursor cursor) {
        CookRecipeResponse.RecipeRow row = new CookRecipeResponse.RecipeRow();
        try {
            row.setRCP_SEQ(cursor.getString(cursor.getColumnIndexOrThrow("RCP_SEQ")));
            row.setRCP_NM(cursor.getString(cursor.getColumnIndexOrThrow("RCP_NM")));
            row.setRCP_WAY2(cursor.getString(cursor.getColumnIndexOrThrow("RCP_WAY2")));
            row.setRCP_PAT2(cursor.getString(cursor.getColumnIndexOrThrow("RCP_PAT2")));
            row.setINFO_WGT(cursor.getString(cursor.getColumnIndexOrThrow("INFO_WGT")));
            row.setINFO_ENG(cursor.getString(cursor.getColumnIndexOrThrow("INFO_ENG")));
            row.setINFO_CAR(cursor.getString(cursor.getColumnIndexOrThrow("INFO_CAR")));
            row.setINFO_PRO(cursor.getString(cursor.getColumnIndexOrThrow("INFO_PRO")));
            row.setINFO_FAT(cursor.getString(cursor.getColumnIndexOrThrow("INFO_FAT")));
            row.setINFO_NA(cursor.getString(cursor.getColumnIndexOrThrow("INFO_NA")));
            row.setHASH_TAG(cursor.getString(cursor.getColumnIndexOrThrow("HASH_TAG")));
            row.setATT_FILE_NO_MAIN(cursor.getString(cursor.getColumnIndexOrThrow("ATT_FILE_NO_MAIN")));
            row.setATT_FILE_NO_MK(cursor.getString(cursor.getColumnIndexOrThrow("ATT_FILE_NO_MK")));
            row.setRCP_PARTS_DTLS(cursor.getString(cursor.getColumnIndexOrThrow("RCP_PARTS_DTLS")));
            row.setMANUAL01(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL01")));
            row.setMANUAL_IMG01(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG01")));
            row.setMANUAL02(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL02")));
            row.setMANUAL_IMG02(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG02")));
            row.setMANUAL03(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL03")));
            row.setMANUAL_IMG03(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG03")));
            row.setMANUAL04(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL04")));
            row.setMANUAL_IMG04(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG04")));
            row.setMANUAL05(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL05")));
            row.setMANUAL_IMG05(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG05")));
            row.setMANUAL06(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL06")));
            row.setMANUAL_IMG06(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG06")));
            row.setMANUAL07(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL07")));
            row.setMANUAL_IMG07(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG07")));
            row.setMANUAL08(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL08")));
            row.setMANUAL_IMG08(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG08")));
            row.setMANUAL09(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL09")));
            row.setMANUAL_IMG09(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG09")));
            row.setMANUAL10(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL10")));
            row.setMANUAL_IMG10(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG10")));
            row.setMANUAL11(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL11")));
            row.setMANUAL_IMG11(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG11")));
            row.setMANUAL12(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL12")));
            row.setMANUAL_IMG12(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG12")));
            row.setMANUAL13(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL13")));
            row.setMANUAL_IMG13(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG13")));
            row.setMANUAL14(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL14")));
            row.setMANUAL_IMG14(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG14")));
            row.setMANUAL15(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL15")));
            row.setMANUAL_IMG15(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG15")));
            row.setMANUAL16(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL16")));
            row.setMANUAL_IMG16(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG16")));
            row.setMANUAL17(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL17")));
            row.setMANUAL_IMG17(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG17")));
            row.setMANUAL18(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL18")));
            row.setMANUAL_IMG18(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG18")));
            row.setMANUAL19(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL19")));
            row.setMANUAL_IMG19(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG19")));
            row.setMANUAL20(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL20")));
            row.setMANUAL_IMG20(cursor.getString(cursor.getColumnIndexOrThrow("MANUAL_IMG20")));
            row.setRCP_NA_TIP(cursor.getString(cursor.getColumnIndexOrThrow("RCP_NA_TIP")));

        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }

        return row;
    }
}