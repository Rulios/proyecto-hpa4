package com.example.proyecto1_hpa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Usuarios {

    private SQLiteDatabase db;
    private String username;



    public Usuarios(SQLiteDatabase db, String username) {
        this.db = DatabaseSingleton.getDatabase();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
        this.update();
    }
    public void save() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", this.username);

        db.insert("usuarios", null, contentValues);
    }

    private void update() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", this.username);
        db.update("usuarios", contentValues, "USERNAME = ?", new String[]{this.username});
    }

    public void delete() {
        db.delete("usuarios", "USERNAME = ?", new String[]{this.username});
    }
    @SuppressLint("Range")
    public long getId(){

        String[] columns = new String[] {"_id"};
        String selection = "USERNAME = ?";
        String[] selectionArgs = new String[]{this.username};

        Cursor c = db.query("usuarios", columns, selection, selectionArgs, null, null, null, "1");
        if (c.moveToFirst()) {
            return c.getInt(c.getColumnIndex("_id"));
        } else {
            return -1;
        }
    }

    public static Cursor findByUsername(String username) {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String[] columns = new String[] {"USERNAME"};
        String selection = "USERNAME = ?";
        String[] selectionArgs = new String[]{username};

        return db.query("usuarios", columns, selection, selectionArgs, null, null, null, "1");
    }
    public static Cursor getAllUsers() {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String query = "SELECT * FROM usuarios";
        return db.rawQuery(query, null);
    }

}
