package com.example.elahi.aplicacionened.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ajedrez {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("ronda")
    @Expose
    private String ronda;

    @SerializedName("dia")
    @Expose
    private String dia;

    @SerializedName("hora")
    @Expose
    private String hora;

    @SerializedName("sede")
    @Expose
    private String sede;

    public Ajedrez(String id, String ronda, String dia, String hora, String sede) {
        this.id = id;
        this.ronda = ronda;
        this.dia = dia;
        this.hora = hora;
        this.sede = sede;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
