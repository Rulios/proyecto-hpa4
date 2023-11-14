package com.example.proyecto1_hpa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ViewPagerFragment extends Fragment {
    final DatosViewPager data;

    public ViewPagerFragment(DatosViewPager data) {
        this.data = data;
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.informacion, container, false);

        // Bind data to your view
        TextView titleTextView = view.findViewById(R.id.textView);
        TextView descriptionTextView = view.findViewById(R.id.textView2);
        ImageView imagen = view.findViewById(R.id.imageView);

        titleTextView.setText(data.getTitulo());
        descriptionTextView.setText(data.getDetalle());
        imagen.setImageResource(data.getImagen());

        return view;
    }
}
