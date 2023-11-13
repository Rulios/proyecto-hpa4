package com.example.proyecto1_hpa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar TB;
    private DrawerLayout DL;

    private NavigationView NV;

    private ViewPager2 viewPagerValores;

    String nombreUsuario;

    Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();

        nombreUsuario = intent.getStringExtra("NOMBRE");
        //añadir código de creación del Usuarios
        //usuario = New Usuarios(nombreUsuario);
        //etc
        //...

        TB=findViewById(R.id.TB);
        setSupportActionBar(TB);
        DL=(DrawerLayout) findViewById(R.id.DL);
        NV=(NavigationView)findViewById(R.id.NV);


        //código para llenar la info del viewpager
        viewPagerValores = findViewById(R.id.viewPager2);
        List <DatosViewPager> lista;
        lista = new ArrayList<DatosViewPager>();

        lista.add(new DatosViewPager(1, "VALOR #1", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new DatosViewPager(2, "VALOR #2", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new DatosViewPager(3, "VALOR #3", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new DatosViewPager(4, "VALOR #4", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new DatosViewPager(5, "VALOR $5", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));

        AdaptadorViewPager viewPagerAdaptador = new AdaptadorViewPager( getSupportFragmentManager(), getLifecycle(), lista);
        viewPagerValores.setAdapter(viewPagerAdaptador);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, DL, TB, R.string.open_nav, R.string.close_nav);
        DL.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState==null){

        }

        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.V1)
                {
                    viewPagerValores.setCurrentItem(0);
                }
                else if (item.getItemId()==R.id.V2)
                {
                    viewPagerValores.setCurrentItem(1);
                }
                DL.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //DatabaseSingleton.init(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu);
        return true;
    }
}