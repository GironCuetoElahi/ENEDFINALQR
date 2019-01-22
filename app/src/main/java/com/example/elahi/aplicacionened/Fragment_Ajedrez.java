package com.example.elahi.aplicacionened;


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


public class Fragment_Ajedrez extends Fragment {
    private List<Clase_futbol> Partido=new ArrayList<Clase_futbol>();
    View view;
    private APIService mAPIService;
    private static final String TAG="FRAGMENT_AJEDREZ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ajedrez2, container, false);


        mAPIService = ApiUtils.getAPIService();
        //Partido.clear();
        Partido();

        PartidoView();

        return view;
    }

   /* private void Partido(){
        //JORNADA1
        Partido.add(new Clase_futbol("JUNTA PREVIA","MEXICALI","CENTRO DE INFORMACIÓN","DOMINGO 13:00",R.drawable.strategy,"JORNADA 1"));
        Partido.add(new Clase_futbol("PRIMERA RONDA","POZA RICA","CENTRO DE INFORMACIÓN","LUNES 09:00",R.drawable.strategy,"JORNADA 1"));

        //JORNADA2
        Partido.add(new Clase_futbol("SEGUNDA RONDA","POZA RICA","CENTRO DE INFORMACIÓN","LUNES 15:00",R.drawable.strategy,"JORNADA 2"));
        Partido.add(new Clase_futbol("TERCERA RONDA","PUEBLA","CENTRO DE INFORMACIÓN","MARTES 09:00",R.drawable.strategy,"JORNADA 2"));

        //JORNADA3
        Partido.add(new Clase_futbol("CUARTA RONDA","PUEBLA","CENTRO DE INFORMACIÓN","MIERCOLES 09:00",R.drawable.strategy,"JORNADA 3"));
        Partido.add(new Clase_futbol("QUINTA RONDA","MEXICALI","CENTRO DE INFORMACIÓN","JUEVES 09:00",R.drawable.strategy,"JORNADA 3"));
        Partido.add(new Clase_futbol("SEXTA RONDA","MEXICALI","CENTRO DE INFORMACIÓN","VIERNES 09:00",R.drawable.strategy,"JORNADA 3"));

    }*/




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

        mAPIService.savePartidosAjedrez("AJEDREZ","J1","F").enqueue(new Callback<PartidosAjedrez>() {
            @Override
            public void onResponse(Call<PartidosAjedrez> call, Response<PartidosAjedrez> response) {
                //if(response.body().getPartidos() != null) {
                    Log.d(TAG, "Respuesta: "+ call.request());
                    final List<Ajedrez> parts = response.body().getPartidos();
                    for(int i=0; i < parts.size(); i++){

                        Partido.add(new Clase_futbol(parts.get(i).getRonda(),"MEXICALI",parts.get(i).getSede(),parts.get(i).getHora(), R.drawable.strategy, act, "0", "" ));
                    }
                    PartidoView();
            }
            @Override
            public void onFailure(Call<PartidosAjedrez> call, Throwable t) {

            }
        });
    }

    private void PartidoView(){
        ArrayAdapter<Clase_futbol> adapter=new MyListAdapter();
        ListView list=(ListView) view.findViewById(R.id.listview);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Clase_futbol>{
        public MyListAdapter(){
            super(getActivity(), R.layout.item_view_ajedrez,Partido);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            Fragment_Ajedrez.ViewHolder holder = null;
            View itemView = convertView;
            if (itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.item_view_ajedrez, parent,false);

                holder = new Fragment_Ajedrez.ViewHolder();

                holder.imageView = (ImageView) itemView.findViewById(R.id.logo) ;
                holder.Equipo1=(TextView) itemView.findViewById(R.id.Equipo1) ;
                holder.Lugar=(TextView) itemView.findViewById(R.id.sede);
                holder.Horario=(TextView) itemView.findViewById(R.id.horario);

                itemView.setTag(holder);}

            else
                holder = (Fragment_Ajedrez.ViewHolder) itemView.getTag();

            Clase_futbol CurrentPartido= Partido.get(position);

            holder.imageView.setImageResource(CurrentPartido.getImagen());
            holder.Equipo1.setText(CurrentPartido.getEquipo1());
            holder.Lugar.setText(CurrentPartido.getSede());
            holder.Horario.setText(CurrentPartido.getHorario());


            return itemView;
        }

    }

    static class ViewHolder{
        ImageView imageView;
        TextView Equipo1;
        TextView Horario;
        TextView Lugar;
    }



}
