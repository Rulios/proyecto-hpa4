package com.example.proyecto1_hpa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Valores {
    private final SQLiteDatabase db;
    private String nombre;
    private String descripcion;
    private int imagen;
    public Valores( String nombre, String descripcion, int imagen) {
        this.db = DatabaseSingleton.getDatabase();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void save() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE_VALOR", this.nombre);
        contentValues.put("DESCRIPCION", this.descripcion);

        db.insert("valores", null, contentValues);
    }

    private void update() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE_VALOR", this.nombre);
        contentValues.put("DESCRIPCION", this.descripcion);
        db.update("valores", contentValues, "NOMBRE_VALOR = ?", new String[]{this.nombre});
    }

    public void delete() {
        db.delete("valores", "NOMBRE_VALOR = ?", new String[]{this.nombre});
    }

    @SuppressLint("Range")
    public long getId(){

        String[] columns = new String[] {"_id"};
        String selection = "NOMBRE_VALOR = ?";
        String[] selectionArgs = new String[]{this.nombre};

        Cursor c = db.query("valores", columns, selection, selectionArgs, null, null, null, "1");
        if (c.moveToFirst()) {
            int id = c.getInt(c.getColumnIndex("_id"));
            c.close();
            return  id;
        } else {
            c.close();
            return -1;
        }
    }

    public static Cursor findByNombre(String nombre_valor) {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String[] columns = new String[] {"NOMBRE_VALOR"};
        String selection = "NOMBRE_VALOR = ?";
        String[] selectionArgs = new String[]{nombre_valor};

        return db.query("valores", columns, selection, selectionArgs, null, null, null, "1");
    }
    public static Cursor getAllValores() {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String query = "SELECT * FROM valores";
        return db.rawQuery(query, null);
    }
}
