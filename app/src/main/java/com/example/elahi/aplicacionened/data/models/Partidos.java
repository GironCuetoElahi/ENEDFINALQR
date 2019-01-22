package com.example.elahi.aplicacionened.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Partidos {
    @SerializedName("partidos")
    @Expose
    private List<PartidoModel> partidos = null;

    public List<PartidoModel> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<PartidoModel> partidos) {
        this.partidos = partidos;
    }


    @Override
    public String toString(){
        String response="{\n\"partidos\"";

        response = response +"\n]\n}";
        return response;
    }

}
