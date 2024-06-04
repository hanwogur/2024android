package com.example.reappstart;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("");
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Members");

        sqLiteDatabase.execSQL("create table Members (" +
                "id varchar(12), " +
                "pw varchar(12)," +
                "name varchar(24)," +
                "phone varchar(15));");

        sqLiteDatabase.execSQL("INSERT INTO Members VALUES ('id','pw','name','phone');"); //테스트용

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void memberInsert(String id, String pw, String name, String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT INTO Members VALUES ('"+id+"', '"+pw+"', '"+name+"', '"+phone+"');");
    }

    public ArrayList<String> login(String id, String pw){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Members Where id = '"+id+"';",null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(pw)){
                ArrayList<String> r = new ArrayList<>();
                r.add(cursor.getString(0));
                r.add(cursor.getString(2));
                r.add(cursor.getString(3));
                cursor.close();
                return r;
            }
        }
        cursor.close();
        return null;
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
