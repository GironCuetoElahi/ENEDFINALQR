package com.example.elahi.aplicacionened;

public class Obj_noticias {

    int imagen;
    String Encabezado;
    String Texto;

    public Obj_noticias (int imagen, String Encabezado, String Texto){
        setImagen(imagen);
        setEncabezado(Encabezado);
        setTexto(Texto);

    }

    public int getImagen() {
        return imagen;
    }

    public String getEncabezado() {
        return Encabezado;
    }

    public String getTexto() {
        return Texto;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setEncabezado(String encabezado) {
        Encabezado = encabezado;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }
}
