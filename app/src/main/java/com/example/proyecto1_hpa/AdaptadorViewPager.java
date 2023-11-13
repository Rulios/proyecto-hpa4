package com.example.proyecto1_hpa;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class AdaptadorViewPager extends FragmentStateAdapter {
    private final List<DatosViewPager> dataList;

    public AdaptadorViewPager(FragmentManager fragmentManager, Lifecycle lifecycle, List<DatosViewPager> dataList) {
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
        DatosViewPager data = dataList.get(position);
        return new ViewPagerFragment(data);
    }
}