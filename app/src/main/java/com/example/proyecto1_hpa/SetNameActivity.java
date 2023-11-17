package com.example.proyecto1_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Clase que controla la vista del layout set_name.xml, vista que pide el nombre de usuario y lo 
//guarda dentro de los SharedPreferences de la app
public class SetNameActivity extends AppCompatActivity {

    //nombres de las propiedades en los SharedPreferences
    private static final String PREFS_NAME = "PrefsUserSesion";
    private static final String KEY_USERNAME = "username";

    //componentes de la vista
    Button button;
    EditText nombre;
    Usuarios usuarioIniciado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);

        //inicialización de la base de datos
        DatabaseSingleton.init(this);

        //identificación de los componentes en el layout
        button = findViewById(R.id.button);
        nombre = findViewById(R.id.nombre);

        //evento click del botón, donde guarda el valor obtenido de nombre usuario 
        //y lo guarda dentro de los SharedPreferences de la app. Para finalmente, hacer un intent al MainActivity
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


    //método de inicio de sesión en la aplicación
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


    //método de cierro de sesión de la aplicación
    //borrando datos del SharedPreferences
    public void logout() {
        // Cerrar la sesión
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_USERNAME);
        editor.apply();
    }
}