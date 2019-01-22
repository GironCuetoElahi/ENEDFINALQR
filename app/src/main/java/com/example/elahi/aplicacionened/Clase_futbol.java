package com.example.elahi.aplicacionened;

public class Clase_futbol {
    int imagen;
    String Equipo1;
    String Equipo2;
    String Sede;
    String Horario;
    String Jornada;
    String Res1;
    String Res2;



    public Clase_futbol(String Equipo1, String Equipo2, String Sede, String Horario,int imagen, String Jornada, String res1, String res2){
        setImagen(imagen);
        setEquipo1(Equipo1);
        setEquipo2(Equipo2);
        setSede(Sede);
        setHorario(Horario);
        setJornada(Jornada);
        setRes1(res1);
        setRes2(res2);

    }

    public int getImagen() {
        return imagen;
    }

    public String getEquipo1() {
        return Equipo1;
    }

    public String getEquipo2() {
        return Equipo2;
    }

    public String getSede() {
        return Sede;
    }

    public String getHorario() {
        return Horario;
    }

    public String getJornada() { return Jornada; }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setEquipo1(String equipo1) {
        Equipo1 = equipo1;
    }

    public void setEquipo2(String equipo2) {
        Equipo2 = equipo2;
    }

    public void setSede(String sede) {
        Sede = sede;
    }

    public void setHorario(String horario) { Horario = horario; }

    public void setJornada(String jornada) { Jornada = jornada; }

    public String getRes1() {
        return Res1;
    }

    public String getRes2() {
        return Res2;
    }

    public void setRes1(String res1) {
        Res1 = res1;
    }

    public void setRes2(String res2) {
        Res2 = res2;
    }
}
