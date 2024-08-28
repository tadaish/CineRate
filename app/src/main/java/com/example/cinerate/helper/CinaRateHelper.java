package com.example.cinerate.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CinaRateHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cinerate.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_MOVIES = "CREATE TABLE Movies (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT NOT NULL UNIQUE, " +
            "description TEXT, " +
            "release_year INTEGER, " +
            "director TEXT, " +
            "duration INTEGER," +
            "genre_id INTERGER, " +
            "language_id INTEGER, " +
            "poster_url TEXT, " +
            "created_at TEXT DEFAULT (DATETIME('now')), " +
            "main_cast TEXT, " +
            "trailer_url TEXT, " +
            "FOREIGN KEY (language_id) REFERENCES Languages(id) ON DELETE SET NULL, " +
            "FOREIGN KEY (genre_id) REFERENCES Genres(id) ON DELETE SET NULL)";

    private static final String CREATE_TABLE_GENRES = "CREATE TABLE Genres (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL UNIQUE);";

    private static final String CREATE_TABLE_LANGUAGES = "CREATE TABLE Languages (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL UNIQUE);";


    private static final String CREATE_TABLE_USERS = "CREATE TABLE Users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL UNIQUE, " +
            "password TEXT NOT NULL, " +
            "role TEXT NOT NULL," +
            "created_at TEXT DEFAULT (DATETIME('now')) );";

    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE Comments (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "movie_id INTEGER, " +
            "user_id INTEGER, " +
            "content TEXT, " +
            "created_at TEXT DEFAULT (DATETIME('now')), " +
            "FOREIGN KEY (movie_id) REFERENCES Movies(id) ON DELETE CASCADE, " +
            "FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE SET NULL);";

    private static final String CREATE_TABLE_RATINGS = "CREATE TABLE Ratings (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "movie_id INTEGER, " +
            "user_id INTEGER, " +
            "comment_id INTERGER, " +
            "rating REAL, " +
            "created_at TEXT DEFAULT (DATETIME('now')), " +
            "FOREIGN KEY (movie_id) REFERENCES Movies(id) ON DELETE CASCADE, " +
            "FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE SET NULL);";

    public CinaRateHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIES);
        sqLiteDatabase.execSQL(CREATE_TABLE_LANGUAGES);
        sqLiteDatabase.execSQL(CREATE_TABLE_GENRES);
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMMENTS);
        sqLiteDatabase.execSQL(CREATE_TABLE_RATINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Movies");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Genres");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Languages");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Comments");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Ratings");
        onCreate(sqLiteDatabase);
    }
}
