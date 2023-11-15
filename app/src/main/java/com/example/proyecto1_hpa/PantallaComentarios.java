package com.example.proyecto1_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;

public class PantallaComentarios extends AppCompatActivity {

    Toolbar TB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_comentarios);

        TB2=(Toolbar) findViewById(R.id.TB2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menuoolbarcomentarios, menu);
        TB2.setNavigationIcon(null);

        return true;
    }

}