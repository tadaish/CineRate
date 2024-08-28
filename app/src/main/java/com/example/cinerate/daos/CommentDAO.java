package com.example.cinerate.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.helper.DatabaseManager;
import com.example.cinerate.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private SQLiteDatabase database;

    public CommentDAO (Context context){
        database = DatabaseManager.getInstance(context).open();
    }


    public List<Comment> getAllCommentsByMovie(int movie_id){
        List<Comment> cmts = new ArrayList<>();
        String query =  "SELECT * " +
                        "FROM Comments c" +
                        "INNER JOIN Movies m ON m.id = c.movie_id " +
                        "WHERE c.id = ?";
        String[] selectionArgs = {String.valueOf(movie_id)};
        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Comment c = new Comment(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("movie_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("content")),
                        cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
                );
                cmts.add(c);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return cmts;
    }

    public long addComment(Comment cmt) {
        ContentValues values = new ContentValues();
        values.put("user_id", cmt.getUser_id());
        values.put("movie_id", cmt.getMovie_id());
        values.put("content", cmt.getContent());

        long result = -1;
        try{
            database.beginTransaction();
            result = database.insert("Comments", null, values);
            if (result == -1) {
                Log.e("CommentDAO", "Có lỗi khi thêm");
            }else {
                Log.i("CommentDAO", "Bình luận đã được thêm: " + result);
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("CommentDAO", "Lỗi", e);
        }finally {
            database.endTransaction();
        }
        return result;
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
}
