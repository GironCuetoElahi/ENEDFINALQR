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

import com.example.elahi.aplicacionened.adapter.PartidoAdapter;
import com.example.elahi.aplicacionened.data.models.PartidoModel;
import com.example.elahi.aplicacionened.data.models.Partidos;
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
public class RvSoftbol extends Fragment {

    private APIService mAPIService;
    private ArrayList<Clase_futbol> partidos=new ArrayList();
    private static final String TAG="FRAGMENT_FUTBOL";
    private PartidoAdapter partidoAdapter;
    private View v;

    public RvSoftbol() {

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

        mAPIService.savePartidos("SOFTBOL",jornadita,"F").enqueue(new Callback<Partidos>() {
            @Override
            public void onResponse(Call<Partidos> call, Response<Partidos> response) {

                Log.d(TAG,"ESTO SERIA UN MILAGRO :  "+response.code());
                Log.d(TAG,"ESTO SERIA UN MILAGRO :  "+call.request());

                if(response.body().getPartidos() != null) {

                    final List<PartidoModel> parts = response.body().getPartidos();
                    for(int i=0; i < parts.size(); i++){

                        partidos.add(new Clase_futbol(parts.get(i).getLocal(),parts.get(i).getVisita(),parts.get(i).getSede(),parts.get(i).getHora(), R.drawable.softbal, act, parts.get(i).getRes1(), parts.get(i).getRes2() ));
                    }
                    vistaPartidos();

                }else{
                    //NOTHING
                }


            }

            @Override
            public void onFailure(Call<Partidos> call, Throwable t) {
                Log.d(TAG,"NO");
                Log.d(TAG,"NO"+t.getMessage());
            }
        });
    }



    private void vistaPartidos(){
        RecyclerView rvfutbol = v.findViewById(R.id.rvpartido);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        partidoAdapter = new PartidoAdapter(partidos,R.layout.cardview_deporte,getActivity());
        rvfutbol.setLayoutManager(linearLayoutManager);
        rvfutbol.setAdapter(partidoAdapter);
    }
    public static RvFutbol newInstance() {

        Bundle args = new Bundle();
        RvFutbol fragment = new RvFutbol();
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
