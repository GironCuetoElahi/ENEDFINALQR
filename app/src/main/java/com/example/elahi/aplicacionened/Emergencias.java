package com.example.elahi.aplicacionened;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Emergencias extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_emergencias, container, false);

        llamarbomb();
        llamarcruz();
        llamarimss();
        llamarpoli();
        return view;
    }




    private void llamarpoli() {
        ImageButton boton = (ImageButton) view.findViewById(R.id.llamar_poli);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:911"));
                startActivity(i);
            }
        });
    }



    private void llamarbomb() {
        ImageButton boton = (ImageButton) view.findViewById(R.id.llamar_bomb);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:911"));
                startActivity(i);
            }
        });
    }

    private void llamarcruz() {
        ImageButton boton = (ImageButton) view.findViewById(R.id.llamar_cruz);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:911"));
                startActivity(i);
            }
        });
    }

    private void llamarimss() {
        ImageButton boton = (ImageButton) view.findViewById(R.id.llamar_imss);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:019515152033"));
                startActivity(i);
            }
        });
    }

}


