package com.example.proyecto1_hpa;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
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
    private Toolbar TB;
    private DrawerLayout DL;

    private NavigationView NV;

    private ViewPager2 viewPagerValores;

    FloatingActionButton FAB;

    String nombreUsuario, nombre_valor;

    Usuarios usuario;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseSingleton.getDatabase();

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nombreUsuario = prefs.getString(KEY_USERNAME, null);



        //Intent intent = getIntent();
        Intent intentComentarios=new Intent(MainActivity.this, PantallaComentarios.class);

        //nombreUsuario = intent.getStringExtra("NOMBRE");
        //añadir código de creación del Usuarios
        //usuario = New Usuarios(nombreUsuario);
        //etc
        //...
        SQLiteDatabase db = DatabaseSingleton.getDatabase();

        TB=findViewById(R.id.TB);
        setSupportActionBar(TB);


        DL=(DrawerLayout) findViewById(R.id.DL);
        NV=(NavigationView)findViewById(R.id.NV);
        FAB=(FloatingActionButton) findViewById(R.id.FAB);
        progressBar = findViewById(R.id.progressBar);

        //código para llenar la info del viewpager
        viewPagerValores = findViewById(R.id.viewPager2);
        List <Valores> lista;
        lista = new ArrayList<Valores>();

        lista.add(new Valores("Respeto", "El respeto es la consideración y valoración especial positiva ante alguien o algo, al que se le reconoce valor social o especial deferencia. Transmite una sensación de admiración por las cualidades buenas o valiosas.", R.drawable.valor_respeto));
        lista.add(new Valores("Responsabilidad", "  Responsabilidad es dar cumplimiento a las obligaciones y ser cuidadoso al tomar decisiones o al realizar algo. La responsabilidad es también el hecho de ser responsable de alguien o de algo.", R.drawable.valor_responsabilidad));
        lista.add(new Valores("Honestidad", "Se entiende por honestidad u honradez a una virtud humana consistente en el amor a la justicia y la verdad por encima del beneficio personal o de la conveniencia.", R.drawable.valor_honestidad));
        lista.add(new Valores("Tolerancia", "La tolerancia es la actitud de la persona que respeta las opiniones, ideas o actitudes de las demás personas aunque no coincidan con las propias.", R.drawable.valor_tolerancia));
        lista.add(new Valores("Amor", "  El amor es el vínculo de afecto que nace de la valoración del otro e inspira el deseo de su bien. Puede verse como un valor o como una propiedad de las relaciones humanas.", R.drawable.valor_amor));

        for (Valores valor: lista) {
            valor.save();
        }

        /*
        Lo borró luego

        lista.get(0).setDescripcion("Cambie la descripción del valor uno y lo actualice en la bd");

        Cursor c = Valores.getAllValores();
        if (c.moveToFirst()){
            try {
                lista.get(1).setDescripcion(c.getString(2));
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
        if (savedInstanceState==null){

        }

        viewPagerValores.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                progressBar.setProgress(position + 1);
                Log.d("Slide position ", "valor: " + position);
                nombre_valor = Valores.findNameById(position+1);
                Log.d("Nombre ", "valor: " + nombre_valor);

            }
        });

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
                }else if (item.getItemId()==R.id.V3)
                {
                    viewPagerValores.setCurrentItem(2);
                }else if (item.getItemId()==R.id.V4)
                {
                    viewPagerValores.setCurrentItem(3);
                }else if (item.getItemId()==R.id.V5)
                {
                    viewPagerValores.setCurrentItem(4);
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