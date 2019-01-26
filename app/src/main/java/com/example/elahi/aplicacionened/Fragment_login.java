package com.example.elahi.aplicacionened;


import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;

public class Fragment_login extends Fragment {

    private TextInputEditText usuario;
    private EditText password;
    private int intentos;
    private Button Login;
    private Toast toast;
    android.support.v4.app.FragmentManager fm;
    android.support.v4.app.FragmentTransaction ft;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

     view = inflater.inflate(R.layout.layout_login, container, false);

        usuario = (TextInputEditText)view.findViewById(R.id.usuario);
        password = (EditText)view.findViewById(R.id.editText);
        Login = (Button)view.findViewById(R.id.button);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar(usuario.getText().toString(), password.getText().toString());
            }
        });

        return view;
    }

    private void validar(String Usuario, String Contrasenia) {

        if ((Usuario.equals("elahi")) && (Contrasenia.equals("1234"))) {

            Intent i = new Intent(getContext(), lector_qr.class);
            getActivity().startActivity(i);




            /*FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment = null;
            fragment = new lector_qr();

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            fragment).commit();*/

        } else
            Toast.makeText(getContext(), "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
    }

}
