package com.ihsan946.sholatq.main;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Madhab;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;
import com.google.android.material.navigation.NavigationView;
import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.menufragment.DzikirFragment;
import com.ihsan946.sholatq.menufragment.JadwalFragment;
import com.ihsan946.sholatq.menufragment.SetelanFragment;
import com.ihsan946.sholatq.model.Sholatqmodel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    Activity activity;
    FragmentTransaction transaction;
    NavigationView navigasi;
    DrawerLayout drawer;
    Sholatqmodel model;
    TextView textView;
    String time_shubuh,time_dzuhur,
            time_asr,time_maghrib,time_isya;
    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 100;



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

        textView = findViewById(R.id.text_utama);
        textView.setText(Preference.getQUOTES(getBaseContext()));
        getJadwalSholat();
//





    }

    private void tampilanFragmentUtama(int item) {
        fragment = null;

        switch (item) {
            case R.id.menu1:
                fragment = new JadwalFragment();
                break;
            case R.id.menu2:
                fragment = new DzikirFragment();
                break;
            case R.id.menu3:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu4:
                fragment = new SetelanFragment();
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

    public void getJadwalSholat(){
//        latitude and longitude
        double latitude,longitude;
        latitude = Double.parseDouble(Preference.getLatitudePreferences(getBaseContext()));
        longitude = Double.parseDouble(Preference.getLongitudePreferences(getBaseContext()));

        Coordinates coordinates = new Coordinates(latitude,longitude);
        DateComponents date = DateComponents.from(new Date());
        CalculationParameters params = CalculationMethod.SINGAPORE.getParameters();
        params.madhab = Madhab.SHAFI;
        PrayerTimes prayer = new PrayerTimes(coordinates,date,params);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.UK);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

//        set value
        time_shubuh = formatter.format(prayer.fajr);
        time_dzuhur = formatter.format(prayer.dhuhr);
        time_asr = formatter.format(prayer.asr);
        time_maghrib = formatter.format(prayer.maghrib);
        time_isya = formatter.format(prayer.isha);

        Preference.setTimeShubuhPreference(getBaseContext(),time_shubuh);
        Preference.setTimeDzuhurPreference(getBaseContext(),time_dzuhur);
        Preference.setTimeAsrPreference(getBaseContext(),time_asr);
        Preference.setTimeMaghribPreference(getBaseContext(),time_maghrib);
        Preference.setTimeIsyaPreference(getBaseContext(),time_isya);
//

    }






}