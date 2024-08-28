package com.example.cinerate.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private static DatabaseManager instance;
    private static SQLiteDatabase database;
    private static CinaRateHelper dbHelper;

    private DatabaseManager(Context context){
        dbHelper = new CinaRateHelper(context);
    }

    public static synchronized DatabaseManager getInstance(Context context){
        if(instance == null){
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    public SQLiteDatabase open(){
        if(database == null || !database.isOpen()){
            database = dbHelper.getWritableDatabase();
        }

        return database;
    }

    public void close(){
        if(database != null && database.isOpen()){
            database.close();
        }
    }
}
