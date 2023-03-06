package com.example.a2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public DB( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserData(name TEXT primary key , Title TEXT,Category TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists UserData");
    }

    public Boolean insert(String name,String Title,String Category){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("name",name);
        contentValues.put("Title",Title);
        contentValues.put("Category",Category);
        long result=DB.insert("UserData",null,contentValues);
        if(result==-1){
            return false;

        }else {
            return true;
        }




    }
    public Cursor getdata (){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from UserData ", null);
        return cursor;


    }
}
