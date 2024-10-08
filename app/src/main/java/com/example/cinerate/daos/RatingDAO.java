package com.example.cinerate.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.helper.DatabaseManager;
import com.example.cinerate.models.Rating;

public class RatingDAO {
    private SQLiteDatabase database;

    public RatingDAO (Context context){
        database = DatabaseManager.getInstance(context).open();
    }


    public long addRating(Rating r) {
        ContentValues values = new ContentValues();
        values.put("movie_id", r.getMovie_id());
        values.put("user_id", r.getUser_id());
        values.put("comment_id", r.getComment_id());
        values.put("rating", r.getRating());

        long result = -1;
        try{
            database.beginTransaction();
            result = database.insert("Ratings", null, values);
            if (result == -1) {
                Log.e("RatingDAO", "Có lỗi khi thêm");
            }else {
                Log.i("RatingDAO", "Đánh giá đã được thêm: " + result);
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("RatingDAO", "Lỗi", e);
        }finally {
            database.endTransaction();
        }
        return result;
    }

    public float getRatingByUser(int comment_id) {
        float rating = 0;
        String query =  "SELECT rating "+
                        "FROM Ratings " +
                        "INNER JOIN Comments ON Comments.id = Ratings.id " +
                        "WHERE movie_id = ?";
        String[] selectionArgs = {String.valueOf(comment_id)};
        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            rating = cursor.getFloat(cursor.getColumnIndexOrThrow("rating"));
            cursor.close();
        }

        return rating;
    }

    public float getAverageRatingByMovie(int movie_id){
        float avg_rating = 0;

        String query =  "SELECT AVG(rating) AS avg_rating " +
                        "FROM Ratings r" +
                        "INNER JOIN Movies m ON m.id = r.movie_id " +
                        "WHERE r.movie_id = ?";
        String[] selectionArgs = {String.valueOf(movie_id)};
        Cursor cursor = database.rawQuery(query,selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            avg_rating = cursor.getFloat(cursor.getColumnIndexOrThrow("avg_rating"));
            cursor.close();
        }

        return avg_rating;
    }

}
