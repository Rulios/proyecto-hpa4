package com.example.proyecto1_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

public class PantallaComentarios extends AppCompatActivity {

    Toolbar TB2;
    String nombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_comentarios);

        Intent intent = getIntent();
        nombreUsuario = intent.getStringExtra("NOMBRE");

        TB2=(Toolbar) findViewById(R.id.TB2);
        setSupportActionBar(TB2);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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

}