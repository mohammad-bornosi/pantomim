package com.e.adabazi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseActivity extends SQLiteAssetHelper {
    private static String DATABASE_NAME="adabaziDatabase.db";
    private static int DATABASE_VERSION=1;
    private SQLiteDatabase db;
    public DatabaseActivity(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        db = getReadableDatabase();
    }
    public SQLiteDatabase getDb(){
        return db ;
    }
}
