package com.example.elahi.aplicacionened;

import org.w3c.dom.Text;

public class Clase_noticia {
    int imagen;
    String Texto;
    String encabezado;

    public Clase_noticia(String Texto, String encabezado, int imagen){
        setTexto(Texto);
        setEncabezado(encabezado);
        setImagen(imagen);

    }

    public int getImagen() {
        return imagen;
    }

    public String getTexto() {
        return Texto;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }
}
