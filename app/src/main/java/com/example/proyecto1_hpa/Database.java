package com.example.proyecto1_hpa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static Database instance = null;

    private Database(Context context) {
        super(context, "proyecto2Database.db", null, 4);
    }

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Código para crear las tablas
        db.execSQL("Create table usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT UNIQUE);");
        db.execSQL("Create table valores (_id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE_VALOR TEXT UNIQUE, DESCRIPCION TEXT);");
        db.execSQL("CREATE TABLE comentarios (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USERNAME TEXT NOT NULL, " +
                "NOMBRE_VALOR TEXT NOT NULL, " +
                "DESCRIPCION TEXT NOT NULL,"+
                "FECHA TEXT NOT NULL,"+
                "FOREIGN KEY (USERNAME) REFERENCES usuarios (USERNAME) ON DELETE CASCADE, " +
                "FOREIGN KEY (NOMBRE_VALOR) REFERENCES valores (NOMBRE_VALOR) ON DELETE CASCADE " +
                ");");

        // Incrementar la versión de la base de datos
        db.setVersion(db.getVersion() + 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Código para actualizar las tablas
        db.execSQL("DROP TABLE IF EXISTS usuarios;");
        db.execSQL("DROP TABLE IF EXISTS valores;");
        db.execSQL("DROP TABLE IF EXISTS comentarios;");
        onCreate(db);
    }
}