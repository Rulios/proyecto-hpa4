package com.example.proyecto1_hpa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "PrefsUserSesion";
    private static final String KEY_USERNAME = "username";
    SharedPreferences prefs;
    String nombreUsuario;
    String nombre_valor;
    private Toolbar TB;
    private DrawerLayout DL;
    private NavigationView NV;
    private ViewPager2 viewPagerValores;
    FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = getIntent();
        Intent intentComentarios=new Intent(MainActivity.this, PantallaComentarios.class);

        //nombreUsuario = intent.getStringExtra("NOMBRE");
        // El usuario se creó en setNameActivity
        //añadir código de creación del Usuarios
        //usuario = New Usuarios(nombreUsuario);
        //etc
        //verificar usuario en sesión

        // Obtener el nombre de usuario de SharedPreferences
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nombreUsuario = prefs.getString(KEY_USERNAME, null);

        TB=findViewById(R.id.TB);
        setSupportActionBar(TB);
        DL=(DrawerLayout) findViewById(R.id.DL);
        NV=(NavigationView)findViewById(R.id.NV);
        FAB=(FloatingActionButton) findViewById(R.id.FAB);

        //código para llenar la info del viewpager
        viewPagerValores = findViewById(R.id.viewPager2);
        List <Valores> lista;
        lista = new ArrayList<Valores>();

        lista.add(new Valores("VALOR #1", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Valores("VALOR #2", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Valores("VALOR #3", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Valores("VALOR #4", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));
        lista.add(new Valores("VALOR #5", "CONTENIDO TEXTO VALOR...", R.drawable.valor_test));

        for (Valores valor: lista) {
            valor.save();
        }

        /*
        //Lo borró luego

        lista.get(0).setDescripcion("Cambie la descripción del valor uno y lo actualice en la bd");

        Cursor c = Valores.getAllValores();
        if (c.moveToFirst()){

            try {
                int columnIndex = c.getColumnIndex("_id");
                int id = c.getInt(columnIndex);
                lista.get(id).setDescripcion(c.getString(2));
                c.moveToNext();
                columnIndex = c.getColumnIndex("_id");
                id = c.getInt(columnIndex);
                lista.get(id).setDescripcion("Este es el usuario activo "+nombreUsuario);
            } catch (Exception e){
                c.moveToLast();
                c.close();
            }
        }
        */


        AdaptadorViewPager viewPagerAdaptador = new AdaptadorViewPager( getSupportFragmentManager(), getLifecycle(), lista);
        viewPagerValores.setAdapter(viewPagerAdaptador);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, DL, TB, R.string.open_nav, R.string.close_nav);
        DL.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState==null){}

        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.V1)
                {
                    viewPagerValores.setCurrentItem(0);
                    nombre_valor = Valores.findNameById(lista.get(0).getId());
                    Toast.makeText(getApplicationContext(), nombre_valor, Toast.LENGTH_LONG).show();


                }
                else if (item.getItemId()==R.id.V2)
                {
                    viewPagerValores.setCurrentItem(1);

                }
                DL.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                intentComentarios.putExtra("VALOR", nombre_valor);
                startActivity(intentComentarios);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.close)
        {
            Intent intentRegresar=new Intent(MainActivity.this, SetNameActivity.class);
            startActivity(intentRegresar);
        }
        return true;
    }

}