package com.example.proyecto1_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    ArrayList<Comentarios> listaComentarios;
    AdaptadorViewComentarios adaptadoComentarios;
    ListView lstComentarios;
    Button btnComentar;
    EditText etComentario;
    Toolbar TB2;
    String nombreUsuario, valor;
    SoftInputAssist SIA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_comentarios);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nombreUsuario = prefs.getString(KEY_USERNAME, null);

        Intent intent = getIntent();
        valor = intent.getStringExtra("VALOR");

        lstComentarios = findViewById(R.id.lsComentarios);
        btnComentar = findViewById(R.id.BTNcoment);
        etComentario = findViewById(R.id.ETcoment);

        listaComentarios = Comentarios.getAllComments(valor);
        if(listaComentarios != null){
            for (Comentarios cm:
                    listaComentarios) {
                Log.d("Comentario: ", cm.getDescripcion());
            }
        }

        adaptadoComentarios = new AdaptadorViewComentarios(getApplicationContext(), listaComentarios);
        Log.d("Tamano array: ", String.valueOf(listaComentarios.size()));
        Log.d("Tamano adaptador: ", String.valueOf(adaptadoComentarios.getCount()));

        lstComentarios.setAdapter(adaptadoComentarios);
        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicarComentario();
            }
        });



        SIA=new SoftInputAssist(this);

        TB2=(Toolbar) findViewById(R.id.TB2);
        setSupportActionBar(TB2);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menuoolbarcomentarios, menu);
        TB2.setNavigationIcon(null);

        return true;
    }

    public void publicarComentario(){
        String descripcion = etComentario.getText().toString();
        if (!descripcion.isEmpty()){
            Comentarios comentario =  new Comentarios(nombreUsuario, valor, descripcion);
            comentario.save();

            listaComentarios.clear();
            listaComentarios.addAll(Comentarios.getAllComments(valor));

            adaptadoComentarios.setDataList(listaComentarios);
            adaptadoComentarios.notifyDataSetChanged();

            etComentario.setText("");
        }
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

    @Override
    protected void onResume(){
        SIA.onResume();
        /*
        listaComentarios = Comentarios.getAllComments(valor);
        AdaptadorViewComentarios adaptadoComentarios = new AdaptadorViewComentarios(getApplicationContext(), listaComentarios);
        lstComentarios.setAdapter(adaptadoComentarios);

         */
        super.onResume();
    }

    @Override
    protected void onPause(){
        SIA.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        SIA.onDestroy();
        super.onDestroy();
    }

}