package com.example.reappstart.ui.n5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "personal.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table Members (" +
                "id varchar(12), " +
                "pw varchar(12)," +
                "name varchar(24)," +
                "phone varchar(15));");

        sqLiteDatabase.execSQL("INSERT INTO Members VALUES ('id','pw','name','phone');"); //테스트용

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Members");
        onCreate(sqLiteDatabase);
    }


    public void memberInsert(String id, String pw, String name, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO Members VALUES ('"+id+"', '"+pw+"', '"+name+"', '"+phone+"');");
        db.close();
    }

    public ArrayList<String> login(String id, String pw){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            db = this.getReadableDatabase();

            // 파라미터화된 쿼리 사용
            cursor = db.rawQuery("SELECT * FROM Members WHERE id = ?", new String[]{id});

            // 데이터가 있는지 확인
            if (cursor.moveToFirst()) {
                // 비밀번호 확인
                if (cursor.getString(cursor.getColumnIndexOrThrow("pw")).equals(pw)) {
                    // 로그인 성공, 필요한 데이터 추가
                    result.add(cursor.getString(cursor.getColumnIndexOrThrow("id")));
                    result.add(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    result.add(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
                    return result; // 로그인 성공 시 결과 반환
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
            Log.v("asdf",result.toString());
        } finally { //모르겠다 다 닫아버림
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return null; // 로그인 실패 시 null 반환
    }

    public ArrayList<String> getMemberId (){ //모든 아이디 가져오는 거
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM Members",null);
        ArrayList<String> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(cursor.getString(1));
        }
        cursor.close();
        return result;
    }
}
