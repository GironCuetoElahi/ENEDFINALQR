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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Noticias extends Fragment {

    public static String text1;
    public static String text2;
    public static String sup1;
    public static String sup2;
    public static String text5;
    public static String text6;

    private static List<Clase_noticia> Noticia=new ArrayList<Clase_noticia>();

    View view;
    private static String DEBUG_TAG;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        buscar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_noticias, container, false);
        NoticiaView();
        return view;
    }

    public static void imprime(String aux) {
        String[] parts = aux.split(";");
        Noticia.clear();
        for(int i=0;i<parts.length-1;i++){
            if (!parts[i+1].equals("")) {
                String[] parts2=parts[i].split("&");
                sup1=parts2[0];
                sup2=parts2[1];
                Noticia(sup1,sup2);
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
        DEBUG_TAG= "http://192.168.88.33:8888/conection.php/";
        new Noticias.DownloadWebpageTask().execute(DEBUG_TAG);

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
    private static void  Noticia(String encab,String texto){
        Noticia.add(new Clase_noticia (encab,texto,R.drawable.news));
    }

    private void NoticiaView(){
        ArrayAdapter<Clase_noticia> adapter=new MyListAdapter();
        ListView list=(ListView) view.findViewById(R.id.listview);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Clase_noticia>{
        public MyListAdapter(){
            super(getActivity(), R.layout.item_view_noticias, Noticia);
        }

        public View getView (int position, View convertView, ViewGroup parent){
            ViewHolder holder = null;
            View itemView = convertView;
            if (itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.item_view_noticias, parent,false);

                holder = new ViewHolder();

                holder.imageView = (ImageView) itemView.findViewById(R.id.logo) ;
                holder.Texto=(TextView) itemView.findViewById(R.id.titulo) ;
                holder.Encabezado=(TextView) itemView.findViewById(R.id.textView);

                itemView.setTag(holder);}

            else
                holder = (ViewHolder) itemView.getTag();

            Clase_noticia CurrentNoticia= Noticia.get(position);

            holder.imageView.setImageResource(CurrentNoticia.getImagen());
            holder.Texto.setText(CurrentNoticia.getTexto());
            holder.Encabezado.setText(CurrentNoticia.getEncabezado());

            return itemView;
        }

    }

    static class ViewHolder{
        ImageView imageView;
        TextView Texto;
        TextView Encabezado;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}



