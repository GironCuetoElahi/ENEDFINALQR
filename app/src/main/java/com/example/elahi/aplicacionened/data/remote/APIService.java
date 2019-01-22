package com.example.elahi.aplicacionened.data.remote;

import com.example.elahi.aplicacionened.data.models.Partidos;
import com.example.elahi.aplicacionened.data.models.PartidosAjedrez;
import com.example.elahi.aplicacionened.data.models.permiso;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIService {
    //@Headers({"Content-Type : application/json" })
    @GET("/partidos")
    Call<Partidos> savePartidos(@Query("disciplina") String disc, @Query("jornada") String jorn, @Query("genero") String gen);

    @GET("/partidos")
    Call<PartidosAjedrez> savePartidosAjedrez(@Query("disciplina") String disc, @Query("jornada") String jorn, @Query("genero") String gen);

    @GET("/acceso/")
    Call<permiso> savePermiso(@Query("id") String id);
}
