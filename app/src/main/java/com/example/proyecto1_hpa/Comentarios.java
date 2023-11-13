package com.example.proyecto1_hpa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Comentarios {
    DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final SQLiteDatabase db;
    private final String username;
    private final String nombre_valor;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;

    public Comentarios(String username, String valor, String descripcion, LocalDate fecha, LocalTime hora) {
        this.db = DatabaseSingleton.getDatabase();
        this.username = username;
        this.nombre_valor = valor;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void save() {
        ContentValues contentValues = new ContentValues();
        String fechaStr = this.fecha.format(dayFormatter);
        String horaStr = this.hora.format(timeFormatter);

        contentValues.put("USERNAME", this.username);
        contentValues.put("NOMBRE_VALOR", this.nombre_valor);
        contentValues.put("DESCRIPCION", this.descripcion);
        contentValues.put("FECHA", fechaStr);
        contentValues.put("HORA", horaStr);

        db.insert("comentarios", null, contentValues);
    }

    private void update() {
        ContentValues contentValues = new ContentValues();
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();

        String fechaStr = this.fecha.format(dayFormatter);
        String horaStr = this.hora.format(timeFormatter);

        contentValues.put("DESCRIPCION", this.descripcion);
        contentValues.put("FECHA", fechaStr);
        contentValues.put("HORA", horaStr);

        db.update("comentarios", contentValues, "USERNAME = ?", new String[]{this.username});
    }

    @SuppressLint("Range")
    public static int getCommentId(String username, String nombre_valor) {
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

    public void delete() {
        int id = Comentarios.getCommentId(this.username, this.nombre_valor);
        db.delete("comentarios", "_id = ?", new String[]{String.valueOf(id)});
    }
    public static Cursor getAllComments(String nombreValor) {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String query = "SELECT * FROM comentarios WHERE NOMBRE_VALOR = ?";
        String[] selectionArgs = new String[]{nombreValor};
        return db.rawQuery(query, selectionArgs);
    }

}
