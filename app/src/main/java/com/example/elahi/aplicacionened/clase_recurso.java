package com.example.elahi.aplicacionened;

public class clase_recurso {

    private int imagen;
    private String Encabezado;

    public clase_recurso (int imagen, String Encabezado){
        setImagen(imagen);
        setEncabezado(Encabezado);

    }

    public int getImagen() {
        return imagen;
    }

    public String getEncabezado() {
        return Encabezado;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setEncabezado(String encabezado) {
        Encabezado = encabezado;
    }
}
