package com.example.elahi.aplicacionened;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Calendario extends Fragment {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("MIAPP", "Estás online");

            Log.d("MIAPP", " Estado actual: " + networkInfo.getState());

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // Estas conectado a un Wi-Fi

                Log.d("MIAPP", " CONECTED " + networkInfo.getExtraInfo());
            }

        } else {
            Toast.makeText(getContext(), "Revisa tu conexion a internet", Toast.LENGTH_LONG);
        }
        view = inflater.inflate(R.layout.fragment_calendario, container, false);
        Toast.makeText(getContext(), "Revisa tu conexion a internet", Toast.LENGTH_LONG);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbarid);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        ViewPagerAdpaterCalendario adapter = new ViewPagerAdpaterCalendario(getFragmentManager());
        adapter.addFragment(new RvFutbol(),"Futbol");
        adapter.addFragment(new RvFutbolFemenil(),"Futbol Femenil");
        adapter.addFragment(new RvBasquetbol(),"Básquetbol");
        adapter.addFragment(new RvBasquetbolFemenil(),"Básquetbol Femenil");
        adapter.addFragment(new RvVoleibol(),"Voleibol");
        adapter.addFragment(new RvVoleibolFemenil(),"Voleibol Femenil");
        adapter.addFragment(new RvVoleibolPlaya(),"Voleibol Playa");
        adapter.addFragment(new RvVoleibolPlayaFemenil(),"Voleibol Playa Femenil");
        adapter.addFragment(new RvBeisbol(), "Béisbol");
        adapter.addFragment(new RvSoftbol(),"Sóftbol");
        adapter.addFragment(new RvAjedrez(),"Ajedrez");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
