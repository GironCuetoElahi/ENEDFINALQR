package com.example.elahi.aplicacionened;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int CALL_PERMISSION_REQUEST_CODE = 1 ;
    private static final int GPS_PERMISSION_REQUEST_CODE = 2 ;
    private DrawerLayout drawer;
    private Noticias noti;
    private Calendario cale;
    private Mapa map;
    private Emergencias emer;
    private fragment_recurso rec;
    private Fragment_login log;
    android.support.v4.app.FragmentManager fm;
    android.support.v4.app.FragmentTransaction ft;

    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen=(ImageView)findViewById(R.id.imageView3);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
            //Si el permiso no se encuentra concedido se solicita
            ActivityCompat.requestPermissions( MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
        } if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_PERMISSION_REQUEST_CODE );

        } else {
            //Si el permiso esá concedico prosigue con el flujo normal
            permissionGranted();
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("MIAPP", "Estás online");

            Log.d("MIAPP", " Estado actual: " + networkInfo.getState());

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // Estas conectado a un Wi-Fi

                Log.d("MIAPP", " CONECTED " + networkInfo.getExtraInfo());
            }

        } else {
            Toast.makeText(getApplicationContext(), "Revisa tu conexion a internet", Toast.LENGTH_LONG);
        }



        cale=new Calendario();
        map=new Mapa();
        noti= new Noticias();
        emer=new Emergencias();
        rec=new fragment_recurso();
        log= new Fragment_login();

        fm= getSupportFragmentManager();
        ft=fm.beginTransaction();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ft.add(R.id.fragment_container,noti);
        ft.add(R.id.fragment_container,map);
        ft.add(R.id.fragment_container,cale);
        ft.add(R.id.fragment_container,emer);
        ft.add(R.id.fragment_container,rec);
        ft.add(R.id.fragment_container,log);
        ft.hide(map);
        ft.hide(noti);
        ft.hide(emer);
        ft.hide(rec);
        ft.hide(log);
        if (savedInstanceState == null) {
            ft.show(cale);
            navigationView.setCheckedItem(R.id.nav_calendario);
        }
        ft.commit();


        imagen.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {

                Uri uri = Uri.parse("https://www.facebook.com/pg/LXII-Evento-Nacional-Estudiantil-Deportivo-Tec-NM-Oaxaca-2018-610649012622622/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }});

    }

    private void permissionGranted() {
        Toast.makeText(MainActivity.this, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.hide(cale);
        ft.hide(noti);
        ft.hide(map);
        ft.hide(emer);
        ft.hide(rec);
        ft.hide(log);
        switch (menuItem.getItemId()){
            case R.id.nav_noticias:
                ft.show(noti);
                ft.commitNow();
                break;
            case R.id.nav_calendario:
                ft.show(cale);
                ft.commitNow();
                break;
            case R.id.nav_ubicacion:
                ft.show(map);
                ft.commitNow();
                break;
            case R.id.nav_seguridad:
                ft.show(emer);
                ft.commitNow();
                break;
            case R.id.nav_recursos:
                ft.show(rec);
                ft.commitNow();
                break;
            case R.id.nav_qr:
                ft.show(log);
                ft.commitNow();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }




}
