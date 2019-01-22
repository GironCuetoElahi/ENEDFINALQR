package com.example.elahi.aplicacionened.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elahi.aplicacionened.Clase_futbol;
import com.example.elahi.aplicacionened.R;

import java.util.ArrayList;


public class AjedrezAdapter extends RecyclerView.Adapter<AjedrezAdapter.AjedrezAdaptador> {
    private ArrayList<Clase_futbol> partidos;
    private int recurso;
    private Activity actividad;
    public AjedrezAdapter(ArrayList<Clase_futbol> partidos,int recurso,Activity actividad){
        this.partidos = partidos;
        this.recurso = recurso;
        this.actividad = actividad;
    }


    @Override
    public AjedrezAdaptador onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(recurso,parent,false);
        return new AjedrezAdaptador(view);
    }

    @Override
    public void onBindViewHolder(AjedrezAdaptador holder, int position) {
        Clase_futbol partido = partidos.get(position);
        holder.equipo1.setText(partido.getEquipo1());
        holder.horario.setText(partido.getHorario());
        holder.sede.setText(partido.getSede());
        holder.logo.setImageResource(partido.getImagen());

    }

    @Override
    public int getItemCount() {
        return partidos.size();
    }

    public class AjedrezAdaptador extends RecyclerView.ViewHolder{
        private ImageView logo;
        private TextView equipo1;
        private TextView sede;
        private TextView horario;



        public AjedrezAdaptador(View itemView) {
            super(itemView);
            this.logo =   itemView.findViewById(R.id.logo);
            this.equipo1= itemView.findViewById(R.id.Equipo1) ;
            this.sede=    itemView.findViewById(R.id.sede) ;
            this.horario =itemView.findViewById(R.id.horario);


        }
    }
}
