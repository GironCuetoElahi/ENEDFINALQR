package com.example.elahi.aplicacionened;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_recurso extends Fragment {
    private static List<clase_recurso> Recurso = new ArrayList<clase_recurso>();
    private static List<String> urls=new ArrayList<>();

    View view;
    public static String texto1;
    public static String sup1;
    public static String sup2;
    private static String DEBUG_TAG;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        buscar();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recurso, container, false);

        RecursoView();
        return view;
    }


    public static void imprime(String aux) {
        String[] parts = aux.split(";");
        Recurso.clear();
        urls.clear();
        for(int i=0;i<parts.length-1;i++){
            if (!parts[i+1].equals("")) {
                String[] parts2=parts[i].split("&");
                sup1=parts2[0];
                sup2=parts2[1];
                Recurso(sup1);
                Url(sup2);
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
        DEBUG_TAG= "http://192.168.88.33:8888/conection_rec.php/";
        new fragment_recurso.DownloadWebpageTask().execute(DEBUG_TAG);

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

    private static void Recurso(String encab){
        Recurso.add(new clase_recurso (R.drawable.folder,encab));
    }

    private static void Url(String url){
        urls.add(url);
    }


    /*private void Recurso() {
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
    }*/

    private void RecursoView(){
        ArrayAdapter<clase_recurso> adapter=new MyListAdapter();
        ListView list=(ListView) view.findViewById(R.id.listview);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0; i<=position; i++){
                    if (position==i){
                    String cadena = urls.get(i).toString();
                    Uri uri = Uri.parse(cadena);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }}
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
