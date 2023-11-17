package com.example.proyecto1_hpa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Usuarios {

    private final SQLiteDatabase db;
    private String username;



    public Usuarios(String username) {
        this.db = DatabaseSingleton.getDatabase();
        this.username = username;
    }

    // No usado
    public String getUsername() {
        return username;
    }
    // No usado
    public void setUsername(String username) {
        int id = getId();
        this.username = username;
        if (id == -1){
            throw new IllegalStateException("Este usuario no esta registrado en la bd: "+this.username);
        } else {
            update(id);
        }
    }
    public void save() {
        String user = Usuarios.findByUsername(this.username);
        if (user == null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("USERNAME", this.username);

            db.insert("usuarios", null, contentValues);
        }

    }

    // No usado
    private void update(int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", this.username);
        db.update("usuarios", contentValues, "_id = ?", new String[]{String.valueOf(id)});
    }

    public void delete() {
        db.delete("usuarios", "USERNAME = ?", new String[]{this.username});
    }

    // No usado
    private int getId(){

        String[] columns = new String[] {"_id"};
        String selection = "USERNAME = ?";
        String[] selectionArgs = new String[]{this.username};

        Cursor c = db.query("usuarios", columns, selection, selectionArgs, null, null, null, "1");
        if (c.moveToFirst()) {
            int columnIndex = c.getColumnIndex("USERNAME");
            int id = c.getInt(columnIndex);
            c.close();
            return  id;
        } else {
            c.close();
            return -1;
        }
    }

    public static String findByUsername(String username) {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String[] columns = new String[] {"USERNAME"};
        String selection = "USERNAME = ?";
        String[] selectionArgs = new String[]{username};

        Cursor c = db.query("usuarios", columns, selection, selectionArgs, null, null, null, "1");

        if (c.moveToFirst()) {
            int columnIndex = c.getColumnIndex("USERNAME");
            String user = c.getString(columnIndex);
            c.close();
            return  user;
        } else {
            c.close();
            return null;
        }
    }
    // No usado
    public static Cursor getAllUsers() {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String query = "SELECT * FROM usuarios";
        return db.rawQuery(query, null);
    }

}
