package com.example.elahi.aplicacionened;


import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Fragment_login extends Fragment {

    private TextInputEditText usuario;
    private EditText password;
    private int intentos;
    private Button Login;
    private Toast toast;
    public static String sup1;
    public static String sup2;
    private static String DEBUG_TAG;

    private static List<String> user= new ArrayList<String>();
    private static List<String> pass=new ArrayList<String>();

    android.support.v4.app.FragmentManager fm;
    android.support.v4.app.FragmentTransaction ft;
    View view;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        buscar();
    }



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

    public static void imprime(String aux) {
        String[] parts = aux.split(";");
        user.clear();
        pass.clear();
        for(int i=0;i<parts.length-1;i++){
            if (!parts[i+1].equals("")) {
                String[] parts2=parts[i].split("&");
                sup1=parts2[0];
                sup2=parts2[1];
                agregaruser(sup1);
                agregarpass(sup2);
            }
        }
    }

    public  boolean isConected(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    public void buscar(){
        //DEBUG_TAG= "https://elahito.000webhostapp.com/php/conection.php";
        DEBUG_TAG= "http://192.168.88.33:8888/conection_user.php/";
        new Fragment_login.DownloadWebpageTask().execute(DEBUG_TAG);

    }

    private static class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "No se puede recuperar la página web. URL puede no ser válida.";
            }
        }

        // onPostExecute muestra el resultado de AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            imprime(result);
        }
    }

    private static String downloadUrl(String myurl) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milisegundos */);
            conn.setConnectTimeout(15000 /* milisegundos */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Inicia la consulta
            conn.connect();
            int response = conn.getResponseCode();
            Log.d( DEBUG_TAG,"La respuesta es: " + response);
            is = conn.getInputStream();
            //Para descargar la página web completa
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String webPage = "";
            String data = "";
            while ((data = reader.readLine()) != null) {
                webPage += data + "\n";
            }
            return webPage;
            // Se asegura de que el InputStream se cierra después de la aplicación deja de usarla.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private static void agregaruser(String User){
        user.add(User);
    }

    private static void agregarpass(String Pass){
        pass.add(Pass);
    }

    private void validar(String Usuario, String Contrasenia) {

        if ((Usuario!=null) && (Contrasenia.equals(Contrasenia!=null))){

        try{

        for (int i=0;i<=user.size();i++){

        if ((Usuario.equals(user.get(i).toString())) && (Contrasenia.equals(pass.get(i).toString()))) {


            Intent a = new Intent(getContext(), lector_qr.class);
            getActivity().startActivity(a);

        }


    }

    }catch(Exception e){
            Toast.makeText(getContext(), "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
    }

        } else {
            Toast.makeText(getContext(), "Por favor Ingresa Usuario y/o Contraseña", Toast.LENGTH_SHORT).show();
        }
    }
}




