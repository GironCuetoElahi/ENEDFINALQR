package com.example.elahi.aplicacionened.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PartidosAjedrez {
    @SerializedName("partidos")
    @Expose
    private List<Ajedrez> partidos = null;

    public List<Ajedrez> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Ajedrez> partidos) {
        this.partidos = partidos;
    }


    @Override
    public String toString(){
        String response="{\n\"partidos\"";

        response = response +"\n]\n}";
        return response;
    }

}
