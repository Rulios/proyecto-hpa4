package com.example.proyecto1_hpa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AdaptadorViewComentarios extends BaseAdapter {
    Context contexto;
    List<Comentarios> dataList;

    private int position;
    private View convertView;
    private ViewGroup parent;

    public AdaptadorViewComentarios(Context contexto, List<Comentarios> dataList) {
        this.contexto = contexto;
        this.dataList = dataList;
    }
    public int getCount() {
        return dataList.size();
    }
    @Override
    public Object getItem(int position){
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {return dataList.get(position).getIdComentario();}

    public void setDataList(List<Comentarios> dataList) {
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        if (convertView == null) {
            LayoutInflater inflate = LayoutInflater.from(contexto);
            vista = inflate.inflate(R.layout.comentario, null);
        }

        // Bind data to your view
        TextView usuarioTextView = vista.findViewById(R.id.Usaurio);
        TextView comentarioTextView = vista.findViewById(R.id.comentario);
        TextView fechaTextView = vista.findViewById(R.id.Fecha);


        usuarioTextView.setText(dataList.get(position).getUsername());
        comentarioTextView.setText(dataList.get(position).getDescripcion());
        fechaTextView.setText(dataList.get(position).getFecha());


        return vista;
    }
}
