package com.example.proyecto1_hpa;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends FragmentStateAdapter {
    private final List<Datos> dataList;

    public Adaptador(FragmentManager fragmentManager, Lifecycle lifecycle, List<Datos> dataList) {
        super(fragmentManager, lifecycle);
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Datos data = dataList.get(position);
        return new ViewPagerFragment(data);
    }
}