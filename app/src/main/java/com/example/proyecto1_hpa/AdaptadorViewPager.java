package com.example.proyecto1_hpa;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

//Declaración de la clase AdaptadorViewPager
public class AdaptadorViewPager extends FragmentStateAdapter {

    //Declaración de los atributos de la clase 
    private final List<Valores> dataList; //almacenamiento de los datos de las slides

    public AdaptadorViewPager(FragmentManager fragmentManager, Lifecycle lifecycle, List<Valores> dataList) {
        super(fragmentManager, lifecycle);
        this.dataList = dataList; //asignación de la lista de datos de las slides
    }

    @Override
    public int getItemCount() {
        return dataList.size(); //obtiene el tamaño de la lista de datos
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Valores data = dataList.get(position);
        return new ViewPagerFragment(data); //devuelve una instancia del fragmento del ViewPager con los datos
    }
}