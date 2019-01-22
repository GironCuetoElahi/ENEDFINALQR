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


public class PartidoAdapter extends RecyclerView.Adapter<PartidoAdapter.PartidoAdaptador> {
    private ArrayList<Clase_futbol> partidos;
    private int recurso;
    private Activity actividad;
    public PartidoAdapter(ArrayList<Clase_futbol> partidos,int recurso,Activity actividad){
        this.partidos = partidos;
        this.recurso = recurso;
        this.actividad = actividad;
    }


    @Override
    public PartidoAdaptador onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(recurso,parent,false);
        return new PartidoAdaptador(view);
    }

    @Override
    public void onBindViewHolder(PartidoAdaptador holder, int position) {
        Clase_futbol partido = partidos.get(position);
        holder.equipo1.setText(partido.getEquipo1());
        holder.equipo2.setText(partido.getEquipo2());
        holder.resEquipo1.setText(partido.getRes1());
        holder.resEquipo2.setText(partido.getRes2());
        holder.horario.setText(partido.getHorario());
        holder.jornada.setText(partido.getJornada());
        holder.sede.setText(partido.getSede());
        holder.logo.setImageResource(partido.getImagen());

    }

    @Override
    public int getItemCount() {
        return partidos.size();
    }

    public class PartidoAdaptador extends RecyclerView.ViewHolder{
        private ImageView logo;
        private TextView equipo1;
        private TextView equipo2;
        private TextView sede;
        private TextView horario;
        private TextView jornada;
        private TextView resEquipo1;
        private TextView resEquipo2;


        public PartidoAdaptador(View itemView) {
            super(itemView);
            this.logo =   itemView.findViewById(R.id.logo);
            this.equipo1= itemView.findViewById(R.id.Equipo1) ;
            this.equipo2= itemView.findViewById(R.id.Equipo2);
            this.sede=    itemView.findViewById(R.id.sede) ;
            this.horario =itemView.findViewById(R.id.horario);
            this.jornada= itemView.findViewById(R.id.jornada);
            this.resEquipo1=itemView.findViewById(R.id.res_equipo1);
            this.resEquipo2=itemView.findViewById(R.id.res_equipo2);

        }
    }
}
