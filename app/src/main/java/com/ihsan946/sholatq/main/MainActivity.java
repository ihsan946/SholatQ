package com.ihsan946.sholatq.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.api.ApiEndpointJadwal;
import com.ihsan946.sholatq.api.ApiEndpointLokasi;
import com.ihsan946.sholatq.api.ApiServiceJadwal;
import com.ihsan946.sholatq.api.ApiServiceLokasi;
import com.ihsan946.sholatq.menufragment.JadwalFragment;
import com.ihsan946.sholatq.model.ApimodelJadwal;
import com.ihsan946.sholatq.model.ApimodelLokasi;
import com.ihsan946.sholatq.model.DatetimeModel;
import com.ihsan946.sholatq.model.Sholatqmodel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    FragmentTransaction transaction;
    NavigationView navigasi;
    DrawerLayout drawer;
    Sholatqmodel model;
    String Hasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(" ");
        }
//

//
        navigasi = findViewById(R.id.nav_menu);
        navigasi.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//

//
        drawer = findViewById(R.id.drawer_menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        toggle.syncState();
//
        navigasi.getMenu().getItem(0).setChecked(true);
//

//        get ip device

        getLokasi();




//



    }

    private void tampilanFragmentUtama(int item) {
        fragment = null;

        switch (item) {
            case R.id.menu1:
                fragment = new JadwalFragment();
                break;


        }

        if (fragment != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flayout_menu, fragment);
            transaction.commit();
        }
        drawer.closeDrawers();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        tampilanFragmentUtama(item.getItemId());
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    public void getLokasi(){
        ApiServiceLokasi api = ApiEndpointLokasi.getClient().create(ApiServiceLokasi.class);

        Call<ApimodelLokasi> call = api.getLokasi(Preference.getIpDevice(getBaseContext()));
        call.enqueue(new Callback<ApimodelLokasi>() {
            @Override
            public void onResponse(Call<ApimodelLokasi> call, Response<ApimodelLokasi> response) {
              Hasil = response.body().hasil;

              getCity();

            }

            @Override
            public void onFailure(Call<ApimodelLokasi> call, Throwable t) {

            }
        });


    }



    public void getCity(){
        String [] pecahkata = Hasil.split("\\n");

//        Log.d("Pecah",pecahkata[0]);
//        Log.d("Pecah",pecahkata[1]);
//        Log.d("Pecah",pecahkata[2]);
//        Log.d("Pecah",pecahkata[3]);
//        Log.d("Pecah",pecahkata[4]);
//        Log.d("Pecah",pecahkata[5]);

        String hasil_pecahkota = pecahkata[3];
        String hasil_pecahlatitude = pecahkata[4];
        String hasil_pecahlongitude = pecahkata[5];

        String [] pecahlatitude = hasil_pecahlatitude.split("\\s");
        String [] pecahlongitude = hasil_pecahlongitude.split("\\s");
        String [] pecahkota = hasil_pecahkota.split("\\s");

//        Log.d("PecahKota",pecahlatitude[0]);
//        Log.d("PecahKota",pecahlatitude[1]);
//        Log.d("PecahKota",pecahlongitude[0]);
//        Log.d("PecahKota",pecahlongitude[1]);

        String Latitude = pecahlatitude[1];
        String Longitude = pecahlongitude[1];
        String Kota = pecahkota[1];

//
//
        Preference.setLatitudePreferences(getBaseContext(),Latitude);
        Preference.setLongitudePreferences(getBaseContext(),Longitude);
        Preference.setKotaPreferences(getBaseContext(),Kota);

//        String latitude,longitude;
//        latitude = ip.getGeolocation().getLatitude();
//        longitude = ip.getGeolocation().getLongitude();

//        Preference.setLatitudePreferences(getBaseContext(),latitude);
//        Preference.setLongitudePreferences(getBaseContext(),longitude);

    }




}