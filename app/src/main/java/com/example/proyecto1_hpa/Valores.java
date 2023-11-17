package com.example.proyecto1_hpa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

// Clase para manejar los valores en la base de datos
public class Valores {
    private final SQLiteDatabase db;
    private String nombre;
    private String descripcion;
    private int imagen;

    // Constructor que inicializa la base de datos y los valores
    public Valores( String nombre, String descripcion, int imagen) {
        this.db = DatabaseSingleton.getDatabase();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Obtiene el nombre del valor
    public String getNombre() {
        return nombre;
    }

    // Cambia el nombre del valor y actualiza la base de datos si existe
    public void setNombre(String nombre) {
        int id = getId();
        this.nombre = nombre;
        if (id == -1){
            throw new IllegalStateException("Este valor no esta registrado en la bd: "+this.nombre);
        } else {
            update(id);
        }
    }

    // Obtiene la descripción del valor
    public String getDescripcion() {
        return descripcion;
    }

    // Cambia la descripción del valor y actualiza la base de datos si existe
    public void setDescripcion(String descripcion) {
        int id = getId();
        this.descripcion = descripcion;
        if (id == -1){
            throw new IllegalStateException("Este valor no esta registrado en la bd: "+this.nombre);
        } else {
            update(id);
        }
    }

    // Obtiene la imagen asociada al valor
    public int getImagen() {
        return imagen;
    }

    // Cambia la imagen asociada al valor
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    // Guarda el valor en la base de datos si no existe
    public void save() {
        Cursor c = Valores.findByNombre(this.nombre);
        if (!c.moveToFirst()){
            ContentValues contentValues = new ContentValues();
            contentValues.put("NOMBRE_VALOR", this.nombre);
            contentValues.put("DESCRIPCION", this.descripcion);

            db.insert("valores", null, contentValues);
        }
        c.close();
    }

    // Actualiza el valor en la base de datos
    private void update(int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE_VALOR", this.nombre);
        contentValues.put("DESCRIPCION", this.descripcion);
        db.update("valores", contentValues, "_id = ?", new String[]{String.valueOf(id)});
    }

    // Elimina el valor de la base de datos
    public void delete() {
        db.delete("valores", "NOMBRE_VALOR = ?", new String[]{this.nombre});
    }

    // Obtiene el ID del valor si existe en la base de datos
    @SuppressLint("Range")
    public int getId(){

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

    // Encuentra el nombre del valor mediante su ID en la base de datos
    public static String findNameById(int id){
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String[] columns = new String[] {"NOMBRE_VALOR"};
        String selection = "_id = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        Cursor c = db.query("valores", columns, selection, selectionArgs, null, null, null, "1");
        if (c.moveToFirst()){
            int columnIndex = c.getColumnIndex("NOMBRE_VALOR");
            String valor = c.getString(columnIndex);
            c.close();
            return  valor;
        } else {
            c.close();
            return null;
        }
    }

    // Encuentra un valor por su nombre en la base de datos
    public static Cursor findByNombre(String nombre_valor) {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String[] columns = new String[] {"NOMBRE_VALOR"};
        String selection = "NOMBRE_VALOR = ?";
        String[] selectionArgs = new String[]{nombre_valor};

        return db.query("valores", columns, selection, selectionArgs, null, null, null, "1");
    }

    // Obtiene todos los valores de la base de datos
    public static Cursor getAllValores() {
        SQLiteDatabase db = DatabaseSingleton.getDatabase();
        String query = "SELECT * FROM valores";
        return db.rawQuery(query, null);
    }


}
