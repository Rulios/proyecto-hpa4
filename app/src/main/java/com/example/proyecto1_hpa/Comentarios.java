package com.example.proyecto1_hpa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Comentarios {
    private SQLiteDatabase db;
    private String username;
    private String nombre_valor;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;

    public Comentarios(SQLiteDatabase db, String username, String valor, String descripcion, LocalDate fecha, LocalTime hora) {
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
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
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
        contentValues.put("USERNAME", this.username);
        db.update("usuarios", contentValues, "USERNAME = ?", new String[]{this.username});
    }

    public void delete() {
        db.delete("usuarios", "USERNAME = ?", new String[]{this.username});
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
