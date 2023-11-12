package com.example.proyecto1_hpa;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
public class MainActivity extends AppCompatActivity {

    private Toolbar TB;
    private DrawerLayout DL;



    private ViewPager2 viewPagerValores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TB=findViewById(R.id.TB);
        setSupportActionBar(TB);
        DL=(DrawerLayout) findViewById(R.id.DL);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, DL, TB, R.string.open_nav, R.string.close_nav);
        DL.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState==null){

        }

        //código para llenar la info del viewpager
        viewPagerValores = findViewById(R.id.viewPager2);
        List <Datos> lista;
        lista = new ArrayList<Datos>();

        lista.add(new Datos(1, "VALOR #1", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Datos(2, "VALOR #2", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Datos(3, "VALOR #3", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Datos(4, "VALOR #4", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Datos(5, "VALOR $5", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));

        Adaptador viewPagerAdaptador = new Adaptador( getSupportFragmentManager(), getLifecycle(), lista);
        viewPagerValores.setAdapter(viewPagerAdaptador);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu);
        return true;
    }
}