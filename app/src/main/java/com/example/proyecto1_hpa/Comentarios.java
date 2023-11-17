package com.example.proyecto1_hpa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Comentarios {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final SQLiteDatabase db;
    private final String username;
    private final String nombre_valor;
    private int idComentario;
    private String fechaStr;

    private String descripcion;
    LocalDateTime fecha;


    public Comentarios(String username, String valor, String descripcion) {
        this.db = DatabaseSingleton.getDatabase();
        this.username = username;
        this.nombre_valor = valor;
        this.descripcion = descripcion;
    }

    public String getUsername() {
        return username;
    }
    public String getNombre_valor() {
        return nombre_valor;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getIdComentario() {
        return idComentario;
    }
    public String getFecha() { return fechaStr; }
    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }
    // No usado
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        update();
    }
    public void save() {
        ContentValues contentValues = new ContentValues();
        this.fecha = LocalDateTime.now();
        fechaStr = fecha.format(formatter);

        contentValues.put("USERNAME", this.username);
        contentValues.put("NOMBRE_VALOR", this.nombre_valor);
        contentValues.put("DESCRIPCION", this.descripcion);
        contentValues.put("FECHA", fechaStr);

        db.insert("comentarios", null, contentValues);
    }
    // No usado
    private void update() {
        ContentValues contentValues = new ContentValues();

        this.fecha = LocalDateTime.now();
        fechaStr = this.fecha.format(formatter);

        contentValues.put("DESCRIPCION", this.descripcion);
        contentValues.put("FECHA", fechaStr);

        db.update("comentarios", contentValues, "_id = ?", new String[]{String.valueOf(this.idComentario)});
    }
    /*
    @SuppressLint("Range")
    public static int getId(String username, String nombre_valor) {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String[] columns = new String[] {"_id"};
        String selection = "USERNAME = ? AND NOMBRE_VALOR = ?";
        String[] selectionArgs = new String[]{username, nombre_valor};

        Cursor cursor = db.query("comentarios", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            cursor.close();
            return id;
        } else {
            cursor.close();
            return -1; // Devuelve -1 si no se encuentra el comentario
        }
    }
    */
    // No usado
    public void delete() {
        db.delete("comentarios", "_id = ?", new String[]{String.valueOf(this.idComentario)});
    }

    @SuppressLint("Range")
    public static ArrayList<Comentarios> getAllComments(String nombreValor) {
        if (nombreValor == null){
            return null;
        }

        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String query = "SELECT * FROM comentarios WHERE NOMBRE_VALOR = ?";
        String[] selectionArgs = new String[]{nombreValor};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        ArrayList<Comentarios> listaComentarios = new ArrayList<>();
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));

            String username = cursor.getString(cursor.getColumnIndex("USERNAME"));
            String descripcion = cursor.getString(cursor.getColumnIndex("DESCRIPCION"));
            String fecha = cursor.getString(cursor.getColumnIndex("FECHA"));
            String valor = cursor.getString(cursor.getColumnIndex("NOMBRE_VALOR"));

            Comentarios comentario = new Comentarios(username, valor, descripcion);
            comentario.setFechaStr(fecha);
            comentario.idComentario = _id;

            listaComentarios.add(comentario);
        }
        cursor.close();

        return listaComentarios;
    }

}
