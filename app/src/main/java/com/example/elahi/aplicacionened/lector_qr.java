package com.example.elahi.aplicacionened;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elahi.aplicacionened.data.remote.APIService;
import com.example.elahi.aplicacionened.data.remote.ApiUtils;
import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.elahi.aplicacionened.data.models.permiso;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class lector_qr extends AppCompatActivity {

    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1 ;
    String lectura;
    private APIService mAPIService;


    View view;

    @Override
    public void onCreate(  Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.layout_lector);
        mAPIService = ApiUtils.getAPIService();

        if (ActivityCompat.checkSelfPermission(lector_qr.this, Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
            //Si el permiso no se encuentra concedido se solicita
            ActivityCompat.requestPermissions(lector_qr.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);}
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
        }

        validar();
    }



    private void validar() {
        ImageButton boton = (ImageButton) findViewById(R.id.imageButton5);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), QrCodeActivity.class);
                    startActivityForResult(i, REQUEST_CODE_QR_SCAN);}

        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
            String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (resultado != null) {
                Toast.makeText(getApplicationContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data != null) {
                lectura = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                //Toast.makeText(getApplicationContext(), "Leído: " + lectura, Toast.LENGTH_SHORT).show();
                mAPIService.savePermiso(lectura).enqueue(new Callback<permiso>() {
                    @Override
                    public void onResponse(Call<permiso> call, Response<permiso> response) {
                        //response.body().isAcceso();
                        if(response.body().isAcceso()){
                            //Toast.makeText(getApplicationContext(), "Concedido el acceso: " + lectura, Toast.LENGTH_LONG).show();
                            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.concedido);
                            mp.start();
                            Dialogsucces exampleDialog = new Dialogsucces();
                            exampleDialog.show(getSupportFragmentManager(), "example dialog");


                        }else{
                            //Toast.makeText(getApplicationContext(), "No Permitir acceso: " + lectura, Toast.LENGTH_LONG).show();
                            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.denied);
                            mp.start();
                            Dialogdenied exampleDialog = new Dialogdenied();
                            exampleDialog.show(getSupportFragmentManager(), "example dialog");
                        }
                    }

                    @Override
                    public void onFailure(Call<permiso> call, Throwable t) {

                    }
                });

            }
        }
    }


}
