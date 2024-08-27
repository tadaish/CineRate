package com.example.cinerate.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.models.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {
    private SQLiteDatabase database;
    private CinaRateHelper dbhelper;

    public LanguageDAO (Context context){
        dbhelper = new CinaRateHelper(context);
    }

    public void open() {
        database = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }

    public List<Language> getAllLanguages() {
        List<Language> langs = new ArrayList<>();
        String query = "SELECT * FROM Languages";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Language lang = new Language(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name"))
                );
                langs.add(lang);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return langs;
    }

    public Language getLangById(int lang_id) {
        Language lang = null;
        String query  = "SELECT * FROM Genres WHERE id = ?";
        String[] selectionArgs = {String.valueOf(lang_id)};
        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                lang = new Language(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name"))
                );
            } while (cursor.moveToNext());
            cursor.close();
        }

        return lang;
    }

    public long addLanguage(Language lang) {
        ContentValues values = new ContentValues();

        values.put("name", lang.getName());

        long result = -1;
        try {
            database.beginTransaction();
            result = database.insert("Languages", null, values);
            if (result == -1) {
                Log.e("LanguageDAO", "Có lỗi khi thêm");
            }else {
                Log.i("languageDAO", "Ngôn ngữ đã được thêm: " + result);
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("LanguageDAO", "Lỗi", e);
        }finally {
            database.endTransaction();
        }

        return result;
    }

    public long updateLanguage(Language lang){
        ContentValues values = new ContentValues();
        values.put("name", lang.getName());

        String whereCls = "id = ?";
        String[] whereArgs = {String.valueOf(lang.getId())};

        int rowsAffected = 0;
        try {
            database.beginTransaction();
            rowsAffected = database.update("Languages", values, whereCls, whereArgs);
            if (rowsAffected == 0) {
                Log.w("LanguageDAO", "Không có gì để cập nhật!");
            } else {
                Log.i("LanguageDAO", "Cập nhật thành công!");
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e("LanguageDAO", "Lối", e);
        }finally {
            database.endTransaction();
        }
        return rowsAffected;
    }

    public void deleteLanguage(int lang_id){
        String whereCls = "id = ?";
        String [] whereArgs = {String.valueOf(lang_id)};

        try {
            database.beginTransaction();
            int rowsDeleted = database.delete("Languages", whereCls, whereArgs);
            if (rowsDeleted == 0){
                Log.w("LanguageDAO", "Không có hàng bị xoá!");
            }else {
                Log.i("LanguageDAO", "Xoá thành công");
                database.setTransactionSuccessful();
            }
        }catch(Exception e){
            Log.e("LanguageDAO", "Lỗi: ", e);
        }finally {
            database.endTransaction();
        }
    }

    public int getLangCount(){
        int count =  0;
        String query = "SELECT COUNT(*) AS lang_count FROM Languages";
        Cursor cursor = database.rawQuery(query,null);

        if (cursor != null && cursor.moveToFirst()){
            count = cursor.getInt(cursor.getColumnIndexOrThrow("lang_count"));
            cursor.close();
        }

        return count;
    }

    public String getLanguageNameById(int lang_id){
        String langName = "";
        String query = "SELECT name FROM Languages WHERE id = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(lang_id)});
        if(cursor != null && cursor.moveToFirst()){
            langName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            cursor.close();
        }
        return langName;
    }
}
