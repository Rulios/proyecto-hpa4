package com.example.proyecto1_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Toolbar TB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TB=findViewById(R.id.TB);
        setSupportActionBar(TB);

        DatabaseSingleton.init(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu);
        return true;
    }
}