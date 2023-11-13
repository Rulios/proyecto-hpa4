package com.example.proyecto1_hpa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseSingleton {
    private static SQLiteDatabase db;
    private static Context context;

    public static synchronized void init(Context context) {
        if (DatabaseSingleton.context == null) {
            DatabaseSingleton.context = context;
            db = Database.getInstance(context).getWritableDatabase();
        }
    }

    public static SQLiteDatabase getDatabase() {
        if (db == null) {
            throw new IllegalStateException("com.example.proyecto1_hpa.DatabaseSingleton is not initialized, call init(Context) method first.");
        }
        return db;
    }
}
