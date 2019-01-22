package com.example.elahi.aplicacionened;

import java.security.Signature;

public class class_emergencia {

        private String nombre;
        private int imagen;
        private String numero;

        public class_emergencia(String nombre, String numero,int idDrawable) {
            this.nombre = nombre;
            this.numero = numero;
            this.imagen = idDrawable;
        }

        public String getNombre() {
            return nombre;
        }

        public int getIdDrawable() {
            return imagen;
        }

        public int getId() {
            return nombre.hashCode();
        }

        public String getNumero(){
            return numero;
    }

        public void setNumero(String numero){
            this.numero=numero;
        }


    }

