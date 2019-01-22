package com.example.elahi.aplicacionened;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elahi.aplicacionened.adapter.AjedrezAdapter;
import com.example.elahi.aplicacionened.adapter.PartidoAdapter;
import com.example.elahi.aplicacionened.data.models.Ajedrez;
import com.example.elahi.aplicacionened.data.models.PartidoModel;
import com.example.elahi.aplicacionened.data.models.Partidos;
import com.example.elahi.aplicacionened.data.models.PartidosAjedrez;
import com.example.elahi.aplicacionened.data.remote.APIService;
import com.example.elahi.aplicacionened.data.remote.ApiUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RvAjedrez extends Fragment {

    private APIService mAPIService;
    private ArrayList<Clase_futbol> partidos=new ArrayList();
    private static final String TAG="FRAGMENT_FUTBOL";
    private AjedrezAdapter ajedrezAdapter;
    private View v;

    public RvAjedrez() {

    }
    private void llenarPartidos(){
        Calendar fecha = Calendar.getInstance();
        // int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        String jornadita="";
        String journal="";
        if(dia==22 && mes==10 ){
            jornadita = "J2";
            journal="JORNADA 2";
        }else if(dia==23 && mes==10 ){
            jornadita = "J3";
            journal="JORNADA 3";
        }else if(dia==24 && mes==10 ){
            jornadita = "S";
            journal="SEMIFINAL";
        }else if(dia==25 && mes==10 ){
            jornadita = "F";
            journal="FINAL";
        } else {
            jornadita = "J1";
            journal="JORNADA 1";
        }

        Log.d(TAG,"DIA :  "+ dia + " MES"+ mes);
        final String act= journal;

        mAPIService.savePartidosAjedrez("AJEDREZ","J1","F").enqueue(new Callback<PartidosAjedrez>() {
            @Override
            public void onResponse(Call<PartidosAjedrez> call, Response<PartidosAjedrez> response) {
                //if(response.body().getPartidos() != null) {
                Log.d(TAG, "Respuesta: "+ call.request());
                final List<Ajedrez> parts = response.body().getPartidos();
                for(int i=0; i < parts.size(); i++){

                    partidos.add(new Clase_futbol(parts.get(i).getRonda(),"MEXICALI",parts.get(i).getSede(),parts.get(i).getHora(), R.drawable.strategy, act, "0", "" ));
                }
                vistaPartidos();
            }
            @Override
            public void onFailure(Call<PartidosAjedrez> call, Throwable t) {

            }
        });
    }



    private void vistaPartidos(){
        RecyclerView rvfutbol = v.findViewById(R.id.rvpartido);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ajedrezAdapter = new AjedrezAdapter(partidos,R.layout.cardview_ajedrez,getActivity());
        rvfutbol.setLayoutManager(linearLayoutManager);
        rvfutbol.setAdapter(ajedrezAdapter);
    }
    public static RvAjedrez newInstance() {

        Bundle args = new Bundle();
        RvAjedrez fragment = new RvAjedrez();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_rvfutbol, container, false);
        mAPIService = ApiUtils.getAPIService();
        partidos.clear();
        llenarPartidos();
        return v;
    }



}
