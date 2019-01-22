package com.example.elahi.aplicacionened;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_recurso extends Fragment {
    private List<clase_recurso> Recurso = new ArrayList<clase_recurso>();

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recurso, container, false);
        Recurso();
        RecursoView();
        return view;
    }

    private void Recurso() {
        //JORNADA1
        Recurso.add(new clase_recurso(R.drawable.folder,"DISTRIBUCION DE AULAS PARA ENTREGA DE GAFETES"));
        Recurso.add(new clase_recurso(R.drawable.folder,"REGLAMENTO DEL COMEDOR"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN FUTBOL VARONIL Y FEMENIL"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN BÁSQUETBOL VARONIL Y FEMENIL"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN BÉISBOL"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN VOLEIBOL SALA VARONIL Y FEMENIL"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN VOLEIBOL PLAYA VARONIL Y FEMANIL"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN ATLETISMO"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN SÓFTBOL"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN AJEDREZ"));
        Recurso.add(new clase_recurso(R.drawable.folder,"PROGRAMACIÓN NATACIÓN"));
    }

    private void RecursoView(){
        ArrayAdapter<clase_recurso> adapter=new MyListAdapter();
        ListView list=(ListView) view.findViewById(R.id.listview);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    //distribucion
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1qHBU1j0Osb88AKmBxpqbX2UxJHTkdam6");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if (position==1){
                    //reglamento
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1NVZdZ8pcIKNxv2teBrzuXFRo966bBfHn");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if (position==2){
                    //futbol
                    Uri uri = Uri.parse("https://drive.google.com/file/d/1wnIe3q2jWN-ttV9NM-DT_usSlIQ8Fd2L/view?usp=sharing");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if (position==3){
                    //basquetbol
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1QWbiSSiAA6vkMH1rvOOuS52VRnn83HoL");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if(position==4){
                    //beisbol
                    Uri uri = Uri.parse("https://drive.google.com/open?id=17auiRjPsaxpdumMMhDItqWboCYhkXzqU");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if (position==5){
                    //voleibol sala
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1L1dJ3dD4KM5K3LIYRQKn38hUvI7DaNEK");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if(position==6){
                    //voleibol playa
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1yiOgdUYlFA96RuKAy18BaHfYHWeUyND3");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if(position==7){
                    //atletismo
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1-JyrrPlfePV40y9yAwYEmqPz6KCPHGg8");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if(position==8){
                    //softbol
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1awBybuUrgfqiYTnRsxf-WLUJte32hH9D");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if(position==9){
                    //ajedrez
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1uPbJuNLhNUE08XCuXJIpv5rP9sBUbTQ6");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                if(position==10){
                    //NATACIÓN
                    Uri uri = Uri.parse("https://drive.google.com/open?id=1QQzYtf1hTAN7PrwrvbpjwAqkCl-0JpFg");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<clase_recurso>{
        public MyListAdapter(){
            super(getActivity(), R.layout.item_view_recursos,Recurso);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            fragment_recurso.ViewHolder holder = null;
            View itemView = convertView;
            if (itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.item_view_recursos, parent,false);

                holder = new fragment_recurso.ViewHolder();

                holder.imageView = (ImageView) itemView.findViewById(R.id.imagen) ;
                holder.principal=(TextView) itemView.findViewById(R.id.Encabezado) ;

                itemView.setTag(holder);}

            else
                holder = (fragment_recurso.ViewHolder) itemView.getTag();

            clase_recurso CurrentPartido = Recurso.get(position);

            holder.imageView.setImageResource(CurrentPartido.getImagen());
            holder.principal.setText(CurrentPartido.getEncabezado());


            return itemView;
        }

    }

    static class ViewHolder {
        ImageView imageView;
        TextView principal;
    }



}
