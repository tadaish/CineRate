package com.example.cinerate.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.helper.DatabaseManager;
import com.example.cinerate.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private SQLiteDatabase database;

    public MovieDAO (Context context){
        database = DatabaseManager.getInstance(context).open();
    }


    public long addMovie(Movie movie){
        ContentValues values = new ContentValues();

        values.put("title", movie.getTitle());
        values.put("description", movie.getDescription());
        values.put("release_year", movie.getRelease_year());
        values.put("director", movie.getDirector());
        values.put("poster_url", movie.getPosterUrl());
        values.put("main_cast", movie.getMainCast());
        values.put("trailer_url", movie.getTrailerUrl());
        values.put("language_id", movie.getLanguageId());
        values.put("genre_id", movie.getGenreId());

        long result = -1;
        try {
            database.beginTransaction();
            result = database.insert("Movies", null, values);
            if (result == -1) {
                Log.e("MovieDAO", "Có lỗi khi thêm");
            }else {
                Log.i("MovieDAO", "Phim đã được thêm: " + result);
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("MovieDAO", "Lỗi", e);
        }finally {
            database.endTransaction();
        }

        return result;
    }


    public Movie getMovieById(int id){
        Movie movie = null;
        String[] columns = {
                "id", "title", "description", "release_year", "director",
                "duration", "poster_url", "main_cast", "trailer_url", "language_id", "genre_id"};
        String selection = "id= ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = database.query("Movies", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            movie = new Movie(
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("release_year")),
                    cursor.getString(cursor.getColumnIndexOrThrow("director")),
                    cursor.getString(cursor.getColumnIndexOrThrow("poster_url")),
                    cursor.getString(cursor.getColumnIndexOrThrow("main_cast")),
                    cursor.getString(cursor.getColumnIndexOrThrow("trailer_url")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("language_id")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("genre_id"))
            );
            cursor.close();
        }
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        String query = "SELECT * FROM Movies";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie movie = new Movie(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("release_year")),
                        cursor.getString(cursor.getColumnIndexOrThrow("director")),
                        cursor.getString(cursor.getColumnIndexOrThrow("poster_url")),
                        cursor.getString(cursor.getColumnIndexOrThrow("main_cast")),
                        cursor.getString(cursor.getColumnIndexOrThrow("trailer_url")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("language_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("genre_id"))
                );
                Log.d("MovieDAO", "Movie: " + movie.getTitle());
                movies.add(movie);
            }while (cursor.moveToNext());

            cursor.close();

        } else {
            Log.i("MovieDAO", "Không có dữ liệu trong bảng Movies");
        }

        return movies;
    }

    public List<Movie> getMoviesByGenre(int genre_id){
        List<Movie> movies = new ArrayList<>();

        String query =  "SELECT m.*" +
                        "FROM Movies m " +
                        "INNER JOIN Genres g on g.id = m.genre_id " +
                        "WHERE g.id = ?";

        String[] selectionArgs = {String.valueOf(genre_id)};

        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie movie = new Movie(
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("release_year")),
                        cursor.getString(cursor.getColumnIndexOrThrow("director")),
                        cursor.getString(cursor.getColumnIndexOrThrow("poster_url")),
                        cursor.getString(cursor.getColumnIndexOrThrow("main_cast")),
                        cursor.getString(cursor.getColumnIndexOrThrow("trailer_url")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("language_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("genre_id"))
                );
                movies.add(movie);
            }while (cursor.moveToNext());

            cursor.close();
        }

        return movies;
    }

    public List<Movie> getMoviesByLanguage(int language_id){
        List<Movie> movies = new ArrayList<>();

        String query =  "SELECT m.* " +
                        "FROM Movies m " +
                        "INNER JOIN Languages l ON l.id = m.language_id " +
                        "WHERE m.language_id = ?";

        String[] selectionArgs = {String.valueOf(language_id)};

        Cursor cursor = database.rawQuery(query, selectionArgs);


        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie movie = new Movie(
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("release_year")),
                        cursor.getString(cursor.getColumnIndexOrThrow("director")),
                        cursor.getString(cursor.getColumnIndexOrThrow("poster_url")),
                        cursor.getString(cursor.getColumnIndexOrThrow("main_cast")),
                        cursor.getString(cursor.getColumnIndexOrThrow("trailer_url")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("language_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("genre_id"))
                );
                movies.add(movie);
            }while (cursor.moveToNext());

            cursor.close();
        }

        return movies;
    }

    public long updateMovie(Movie movie) {
        ContentValues values = new ContentValues();
        values.put("title", movie.getTitle());
        values.put("description", movie.getDescription());
        values.put("release_year", movie.getRelease_year());
        values.put("director", movie.getDirector());
        values.put("poster_url", movie.getPosterUrl());
        values.put("main_cast", movie.getMainCast());
        values.put("trailer_url", movie.getTrailerUrl());
        values.put("language_id", movie.getLanguageId());
        values.put("genre_id", movie.getGenreId());

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(movie.getId())};

        int rowsAffected = 0;
        try {
            database.beginTransaction();
            rowsAffected = database.update("Movies", values, whereClause, whereArgs);
            if (rowsAffected == 0) {
                Log.w("MovieDAO", "Không có gì để cập nhật!");
            } else {
                Log.i("MovieDAO", "Update thành công!");
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e("MovieDAO", "Lối", e);
        } finally {
            database.endTransaction();
        }
        return rowsAffected;
    }

    public void deleteMovie(int movie_id){
        String whereClause = "id = ?";
        String [] whereArgs = {String.valueOf(movie_id)};

        try {
            database.beginTransaction();
            int rowsDeleted = database.delete("Movies", whereClause, whereArgs);
            if (rowsDeleted == 0){
                Log.w("MovieDAO", "Không có hàng bị xoá!");
            }else {
                Log.i("MovieDAO", "Xoá thành công");
                database.setTransactionSuccessful();
            }
        }catch(Exception e){
            Log.e("MovieDAO", "Lỗi: ", e);
        } finally {
            database.endTransaction();
        }
    }

    public int getMovieCount(){
        int count =  0;
        String query = "SELECT COUNT(*) AS movie_count FROM Movies";
        Cursor cursor = database.rawQuery(query,null);

        if (cursor != null && cursor.moveToFirst()){
            count = cursor.getInt(cursor.getColumnIndexOrThrow("movie_count"));
            cursor.close();
        }

        return count;
    }

    public List<Movie> getMoviesByTitle(String movieTitle){
        List<Movie> movies = new ArrayList<>();

        String query =  "SELECT *" +
                "FROM Movies " +
                "WHERE title LIKE ?";

        String[] selectionArgs = {"%" + movieTitle + "%"};

        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie movie = new Movie(
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("release_year")),
                        cursor.getString(cursor.getColumnIndexOrThrow("director")),
                        cursor.getString(cursor.getColumnIndexOrThrow("poster_url")),
                        cursor.getString(cursor.getColumnIndexOrThrow("main_cast")),
                        cursor.getString(cursor.getColumnIndexOrThrow("trailer_url")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("language_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("genre_id"))
                );
                movies.add(movie);
            }while (cursor.moveToNext());

            cursor.close();
        }
        return movies;
    }

}
