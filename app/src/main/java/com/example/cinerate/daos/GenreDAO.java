package com.example.cinerate.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.models.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private SQLiteDatabase database;
    private CinaRateHelper dbhelper;

    public GenreDAO (Context context){
        dbhelper = new CinaRateHelper(context);
    }

    public void open() {
        database = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM Genres";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Genre genre = new Genre(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name"))
                );
                genres.add(genre);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return genres;
    }

    public Genre getGenreById(int genre_id) {
        Genre genre = null;
        String query  = "SELECT * FROM Genres WHERE id = ?";
        String[] selectionArgs = {String.valueOf(genre_id)};
        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                    genre = new Genre(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name"))
                );
            } while (cursor.moveToNext());
            cursor.close();
        }

        return genre;
    }

    public long addGenre(Genre genre) {
        ContentValues values = new ContentValues();

        values.put("name", genre.getName());

        long result = -1;
        try {
            database.beginTransaction();
            result = database.insert("Movies", null, values);
            if (result == -1) {
                Log.e("GenreDAO", "Có lỗi khi thêm");
            }else {
                Log.i("GenreDAO", "Thể loại đã được thêm: " + result);
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("GenreDAO", "Lỗi", e);
        } finally {
            database.endTransaction();
        }

        return result;
    }

    public long updateGenre(Genre genre){
        ContentValues values = new ContentValues();
        values.put("name", genre.getName());

        String whereCls = "id = ?";
        String[] whereArgs = {String.valueOf(genre.getId())};

        int rowsAffected = 0;
        try {
            database.beginTransaction();
            rowsAffected = database.update("Genres", values, whereCls, whereArgs);
            if (rowsAffected == 0) {
                Log.w("GenreDAO", "Không có gì để cập nhật!");
            } else {
                Log.i("GenreDAO", "Update thành công!");
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e("GenreDAO", "Lối", e);
        } finally {
            database.endTransaction();
        }
        return rowsAffected;
    }

    public void deleteGenre(int genre_id){
        String whereCls = "id = ?";
        String [] whereArgs = {String.valueOf(genre_id)};

        try {
            database.beginTransaction();
            int rowsDeleted = database.delete("Genres", whereCls, whereArgs);
            if (rowsDeleted == 0){
                Log.w("GenreDAO", "Không có hàng bị xoá!");
            }else {
                Log.i("GenreDAO", "Xoá thành công");
                database.setTransactionSuccessful();
            }
        }catch(Exception e){
            Log.e("GenreDAO", "Lỗi: ", e);
        } finally {
            database.endTransaction();
        }
    }

    public int getGenreCount(){
        int count =  0;
        String query = "SELECT COUNT(*) AS genre_count FROM Genres";
        Cursor cursor = database.rawQuery(query,null);

        if (cursor != null && cursor.moveToFirst()){
            count = cursor.getInt(cursor.getColumnIndexOrThrow("genre_count"));
            cursor.close();
        }

        return count;
    }
}
