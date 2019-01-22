package com.example.elahi.aplicacionened.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartidoModel {

    public PartidoModel(String id, String local, String visita, String sede, String jornada, String hora, String genero, String resultado1, String resultado2) {
        this.id = id;
        this.local = local;
        this.visita = visita;
        this.sede = sede;
        Jornada = jornada;
        this.hora = hora;
        this.genero = genero;
        this.resultado1 = resultado1;
        this.resultado2 = resultado2;
    }
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("local")
    @Expose
    private String local;

    @SerializedName("visita")
    @Expose
    private String visita;

    @SerializedName("sede")
    @Expose
    private String sede;

    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    @SerializedName("jornada")
    @Expose
    private String Jornada;

    @SerializedName("hora")
    @Expose
    private String hora;

    @SerializedName("disciplina")
    @Expose
    private String genero;

    @SerializedName("resultado1")
    @Expose
    private String resultado1;

    @SerializedName("resultado2")
    @Expose
    private String resultado2;




    public String getRes1() {
        return resultado1;
    }

    public void setRes1(String resultado1) {
        this.resultado1 = resultado1;
    }

    public String getRes2() {
        return resultado2;
    }

    public void setRes2(String resultado2) {
        this.resultado2 = resultado2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getJornada() {
        return Jornada;
    }

    public void setJornada(String jornada) {
        Jornada = jornada;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }




}
