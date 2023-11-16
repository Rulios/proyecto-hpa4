package com.example.proyecto1_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetNameActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "PrefsUserSesion";
    private static final String KEY_USERNAME = "username";
    Button button;
    EditText nombre;

    Usuarios usuarioIniciado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);

        DatabaseSingleton.init(this);

        button = findViewById(R.id.button);
        nombre = findViewById(R.id.nombre);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(SetNameActivity.this, MainActivity.class);

            //intent.putExtra("NOMBRE", nombre.getText().toString());

            // Obtener el nombre de usuario de SharedPreferences
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String preUsername = prefs.getString(KEY_USERNAME, null);

            String username = nombre.getText().toString();
            if (!username.isEmpty()){
                if (preUsername != null) {
                    // Si hay un nombre de usuario en SharedPreferences, entonces hay una sesión iniciada
                    logout(); //cerramos la sesión previa
                    login(username);
                } else {
                    // Si no hay un nombre de usuario en SharedPreferences, entonces no hay una sesión iniciada
                    login(username);
                }

                startActivity(intent);
            }
        });
    }

    public void login(String username) {
        // Iniciar la sesión
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String user = Usuarios.findByUsername(username);
        if (user == null) {
            //si el usuario ingresado no esta registrado se registra en la bd
            usuarioIniciado = new Usuarios(username);
            usuarioIniciado.save();
        }

        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public void logout() {
        // Cerrar la sesión
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_USERNAME);
        editor.apply();
    }

}