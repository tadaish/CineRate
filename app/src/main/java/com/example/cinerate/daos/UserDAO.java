package com.example.cinerate.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cinerate.helper.CinaRateHelper;
import com.example.cinerate.helper.DatabaseManager;
import com.example.cinerate.models.User;
import com.example.cinerate.utils.PasswordUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteDatabase database;

    public UserDAO (Context context){
        database = DatabaseManager.getInstance(context).open();
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                User u = new User(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("username")),
                        cursor.getString(cursor.getColumnIndexOrThrow("password")),
                        cursor.getString(cursor.getColumnIndexOrThrow("role"))
                );
                users.add(u);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return users;
    }
    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM Users WHERE username = ?";
        String[] selectionArgs = { username };
        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password")),
                    cursor.getString(cursor.getColumnIndexOrThrow("role"))
            );
            cursor.close();
        }
        return user;
    }

    public User getUserId(int user_id) {
        User user = null;
        String query  = "SELECT * FROM Users WHERE id = ?";
        String[] selectionArgs = {String.valueOf(user_id)};
        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                user = new User(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("username")),
                        cursor.getString(cursor.getColumnIndexOrThrow("password")),
                        cursor.getString(cursor.getColumnIndexOrThrow("role"))
                );
            } while (cursor.moveToNext());
            cursor.close();
        }

        return user;
    }

    public long addUser(User user) {
        ContentValues values = new ContentValues();

        String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
        values.put("username", user.getUsername());
        values.put("password", hashedPassword);
        values.put("role", user.getRole());

        long result = -1;
        try {
            database.beginTransaction();
            result = database.insert("Users", null, values);
            if (result == -1) {
                Log.e("UserDAO", "Có lỗi khi thêm");
            }else {
                Log.i("UserDAO", "Người dùng đã được thêm: " + result);
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("UserDAO", "Lỗi", e);
        }finally {
            database.endTransaction();
        }

        return result;
    }

    public long updateUser(User user){
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", PasswordUtils.hashPassword(user.getPassword()));
        values.put("role", user.getRole());

        String whereCls = "id = ?";
        String[] whereArgs = {String.valueOf(user.getId())};

        int rowsAffected = 0;
        try {
            database.beginTransaction();
            rowsAffected = database.update("Users", values, whereCls, whereArgs);
            if (rowsAffected == 0) {
                Log.w("UserDAO", "Không có gì để cập nhật!");
            } else {
                Log.i("UserDAO", "Update thành công!");
                database.setTransactionSuccessful();
            }

        } catch (Exception e) {
            Log.e("UserDAO", "Lối", e);
        } finally {
            database.endTransaction();
        }
        return rowsAffected;
    }

    public void deleteUser(int user_id){
        String whereCls = "id = ?";
        String [] whereArgs = {String.valueOf(user_id)};

        try {
            database.beginTransaction();
            int rowsDeleted = database.delete("Users", whereCls, whereArgs);
            if (rowsDeleted == 0){
                Log.w("UserDAO", "Không có hàng bị xoá!");
            }else {
                Log.i("UserDAO", "Xoá thành công");
                database.setTransactionSuccessful();
            }
        }catch(Exception e){
            Log.e("UserDAO", "Lỗi: ", e);
        }finally {
            database.endTransaction();
        }
    }

    public int getUserCount(){
        int count =  0;
        String query = "SELECT COUNT(*) AS user_count FROM Users";
        Cursor cursor = database.rawQuery(query,null);

        if (cursor != null && cursor.moveToFirst()){
            count = cursor.getInt(cursor.getColumnIndexOrThrow("user_count"));
            cursor.close();
        }

        return count;
    }
}
