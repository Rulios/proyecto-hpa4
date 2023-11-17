package com.example.proyecto1_hpa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

//Clase para crear el fragmento del slide e inflarlo con los datos correspondientes en el layout informacion.xml
public class ViewPagerFragment extends Fragment {

    //propiedades de la clase
    final Valores data;

    //constructor de la clase 
    public ViewPagerFragment(Valores data) {
        this.data = data; //inicialización de los datos en la propiedad de la clase
    }


    //infla los datos en el layout de informacion.xml en su espacio correspondiente
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.informacion, container, false);

        // registro de los espacios (componentes) correspondientes
        TextView titleTextView = view.findViewById(R.id.textView);
        TextView descriptionTextView = view.findViewById(R.id.textView2);
        ImageView imagen = view.findViewById(R.id.imageView);

        //asignación de los valores en los componentes
        titleTextView.setText(data.getNombre());
        descriptionTextView.setText(data.getDescripcion());
        imagen.setImageResource(data.getImagen());

        return view;
    }
}
