package com.example.proyecto1_hpa;

public class DatosViewPager {
    private int Id;
    private String Titulo;
    private String Detalle;
    private int Imagen;

    public DatosViewPager(int id, String titulo, String detalle, int imagen) {
        Id = id;
        Titulo = titulo;
        Detalle = detalle;
        Imagen = imagen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}
