package com.example.proyecto1_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class PantallaComentarios extends AppCompatActivity {
    private static final String PREFS_NAME = "PrefsUserSesion";
    private static final String KEY_USERNAME = "username";
    SharedPreferences prefs;
    String nombreUsuario, valor;
    AdaptadorViewComentarios adaptadorComentario;
    ArrayList<Comentarios> listaComentarios;
    Toolbar TB2;
    ListView lstComentarios;
    EditText ETcoment;
    Button comentar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_comentarios);



        // Obtener el nombre de usuario de SharedPreferences
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nombreUsuario = prefs.getString(KEY_USERNAME, null);

        Intent intent = getIntent();
        valor = intent.getStringExtra("VALOR");
        lstComentarios = findViewById(R.id._dynamic);
        if(valor != null){
            listaComentarios = Comentarios.getAllComments(valor);
            adaptadorComentario = new AdaptadorViewComentarios(getApplicationContext(), listaComentarios);
            lstComentarios.setAdapter(adaptadorComentario);
        }

        ETcoment = findViewById(R.id.ETcoment);
        TB2=(Toolbar) findViewById(R.id.TB2);
        setSupportActionBar(TB2);

        ETcoment.setHint(nombreUsuario);
        comentar = findViewById(R.id.BTNcoment);
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicarComentario();
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menuoolbarcomentarios, menu);
        TB2.setNavigationIcon(null);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.returnButton)
        {
            Intent intentRegresarVal=new Intent(PantallaComentarios.this, MainActivity.class);
            startActivity(intentRegresarVal);
        }
        return true;
    }

    public void publicarComentario(){
        if (nombreUsuario != null){
            String descripcion = ETcoment.getText().toString();
            if (!descripcion.isEmpty()){
                Comentarios comentario = new Comentarios(nombreUsuario, valor, descripcion);
                comentario.save();
                listaComentarios.add(comentario);
                adaptadorComentario.setDataList(listaComentarios);
                adaptadorComentario.notifyDataSetChanged();
            }
        }
    }

}