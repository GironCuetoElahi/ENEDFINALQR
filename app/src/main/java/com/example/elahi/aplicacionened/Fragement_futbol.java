package com.example.elahi.aplicacionened;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.elahi.aplicacionened.data.models.PartidoModel;
import com.example.elahi.aplicacionened.data.models.Partidos;
import com.example.elahi.aplicacionened.data.remote.APIService;
import com.example.elahi.aplicacionened.data.remote.ApiUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragement_futbol extends Fragment {

    private APIService mAPIService;

    private List<Clase_futbol> Partido=new ArrayList<Clase_futbol>();

        //private List<Clase_futbol> Partido=new ArrayList<Clase_futbol>();

    public static String text1;
    public static String text2;
    public static String sup1;
    public static String sup2;
    public static String text5;
    public static String text6;
    private static String DEBUG_TAG;

        View view;





    private static final String TAG="FRAGMENT_FUTBOL";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_futbol, container, false);

        mAPIService = ApiUtils.getAPIService();
       // Partido.clear();
        Partido();

        PartidoView();

        return view;
    }

    private void Partido(){


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

        mAPIService.savePartidos("FUTBOL",jornadita,"M").enqueue(new Callback<Partidos>() {
            @Override
            public void onResponse(Call<Partidos> call, Response<Partidos> response) {

                Log.d(TAG,"ESTO SERIA UN MILAGRO :  "+response.code());
                Log.d(TAG,"ESTO SERIA UN MILAGRO :  "+call.request());

                if(response.body().getPartidos() != null) {

                    final List<PartidoModel> parts = response.body().getPartidos();
                    for(int i=0; i < parts.size(); i++){

                        Partido.add(new Clase_futbol(parts.get(i).getLocal(),parts.get(i).getVisita(),parts.get(i).getSede(),parts.get(i).getHora(), R.drawable.noticiafut, act, parts.get(i).getRes1(), parts.get(i).getRes2() ));
                    }
                    PartidoView();
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

     private void PartidoView(){
        ArrayAdapter<Clase_futbol>adapter=new MyListAdapter();
         ListView list=(ListView) view.findViewById(R.id.listview);
         list.setAdapter(adapter);
     }

    private class MyListAdapter extends ArrayAdapter<Clase_futbol>{
        public MyListAdapter(){
            super(getActivity(), R.layout.item_view,Partido);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            ViewHolder holder = null;
            View itemView = convertView;
            if (itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.item_view, parent,false);

            holder = new ViewHolder();

            holder.imageView = (ImageView) itemView.findViewById(R.id.logo) ;
            holder.Equipo1=(TextView) itemView.findViewById(R.id.Equipo1) ;
            holder.Equipo2=(TextView) itemView.findViewById(R.id.Equipo2);
            holder.Sede=(TextView) itemView.findViewById(R.id.sede) ;
            holder.Horario=(TextView) itemView.findViewById(R.id.horario);
            holder.Jornada=(TextView) itemView.findViewById(R.id.jornada);
            holder.Res1=(TextView) itemView.findViewById(R.id.res_equipo1);
            holder.Res2=(TextView) itemView.findViewById(R.id.res_equipo2);
                itemView.setTag(holder);}

            else
                holder = (ViewHolder) itemView.getTag();

            Clase_futbol CurrentPartido= Partido.get(position);

            holder.imageView.setImageResource(CurrentPartido.getImagen());
            holder.Equipo1.setText(CurrentPartido.getEquipo1());
            holder.Equipo2.setText(CurrentPartido.getEquipo2());
            holder.Sede.setText(CurrentPartido.getSede());
            holder.Horario.setText(CurrentPartido.getHorario());
            holder.Jornada.setText(CurrentPartido.getJornada());
            holder.Res1.setText(CurrentPartido.getRes1());
            holder.Res2.setText(CurrentPartido.getRes2());

            return itemView;
        }

    }

    static class ViewHolder{
        ImageView imageView;
        TextView Equipo1;
        TextView Equipo2;
        TextView Sede;
        TextView Horario;
        TextView Jornada;
        TextView Res1;
        TextView Res2;

    }
}