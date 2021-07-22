package com.ihsan946.sholatq.main;

import android.annotation.SuppressLint;
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
import com.ihsan946.sholatq.sharedpreferenced.Preference;
import com.ihsan946.sholatq.utils.BroadcastReceiverSholat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    FragmentTransaction transaction;
    NavigationView navigasi;
    DrawerLayout drawer;
    TextView textView;
    String time_shubuh,time_dzuhur,
            time_asr,time_maghrib,time_isya,tanggal_kini;





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
        navigasi = findViewById(R.id.nav_menu);
        navigasi.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
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
//

    }

    @Override
    protected void onStart() {
        super.onStart();
        getJadwalSholat();
    }

    @SuppressLint("NonConstantResourceId")
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

        if(!time_shubuh.equals(Preference.getTimeShubuhPreference(getBaseContext()))){
            Preference.setTimeShubuhPreference(getBaseContext(),time_shubuh);
        }
        if(!time_dzuhur.equals(Preference.getTimeDzuhurPreference(getBaseContext()))){
            Preference.setTimeDzuhurPreference(getBaseContext(),time_dzuhur);
        }
        if(!time_asr.equals(Preference.getTimeAsrPreference(getBaseContext()))){
            Preference.setTimeAsrPreference(getBaseContext(),time_asr);
        }
        if(!time_maghrib.equals(Preference.getTimeMaghribPreference(getBaseContext()))){
            Preference.setTimeMaghribPreference(getBaseContext(),time_maghrib);
        }
        if(!time_isya.equals(Preference.getTimeIsyaPreference(getBaseContext()))){
            Preference.setTimeIsyaPreference(getBaseContext(),time_isya);
        }
        setNotifikasiSholat();
//

    }

    public void setNotifikasiSholat(){
        Date date = new Date();
        int ALARM_CODE_SHUBUH = 100;
        int ALARM_CODE_DZUHUR = 101;
        int ALARM_CODE_ASR = 102;
        int ALARM_CODE_MAGHRIB = 103;
        int ALARM_CODE_ISYA = 104;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        tanggal_kini = formatter.format(date);
        BroadcastReceiverSholat broadcastReceiverSholat = new BroadcastReceiverSholat();
//
        if(Preference.getStatusShubuhPreference(getBaseContext()) == true){
            if(!time_shubuh.equals(Preference.getTimeShubuhPreference(getBaseContext()))){
                broadcastReceiverSholat.cancelAlarm(getBaseContext(),ALARM_CODE_SHUBUH);
                broadcastReceiverSholat.setRepeatingAlarm(getBaseContext(),ALARM_CODE_SHUBUH,
                        Preference.getTimeShubuhPreference(getBaseContext()),"Shubuh",tanggal_kini);
            }

        }

        if(Preference.getStatusDzuhurPreference(getBaseContext()) == true){
            if(!time_dzuhur.equals(Preference.getTimeDzuhurPreference(getBaseContext()))){
                broadcastReceiverSholat.cancelAlarm(getBaseContext(),ALARM_CODE_DZUHUR);
                broadcastReceiverSholat.setRepeatingAlarm(getBaseContext(),ALARM_CODE_DZUHUR,
                        Preference.getTimeDzuhurPreference(getBaseContext()),"Dzuhur",tanggal_kini);
            }
        }

        if(Preference.getStatusAsrPreference(getBaseContext()) == true){
            if(!time_asr.equals(Preference.getTimeAsrPreference(getBaseContext()))){
                broadcastReceiverSholat.cancelAlarm(getBaseContext(),ALARM_CODE_ASR);
                broadcastReceiverSholat.setRepeatingAlarm(getBaseContext(),ALARM_CODE_ASR,
                        Preference.getTimeAsrPreference(getBaseContext()),"Ashar",tanggal_kini);
            }
        }

        if(Preference.getStatusMaghribPreference(getBaseContext()) == true){
            if(!time_maghrib.equals(Preference.getTimeMaghribPreference(getBaseContext()))){
                broadcastReceiverSholat.cancelAlarm(getBaseContext(),ALARM_CODE_MAGHRIB);
                broadcastReceiverSholat.setRepeatingAlarm(getBaseContext(),ALARM_CODE_MAGHRIB,
                        Preference.getTimeMaghribPreference(getBaseContext()),"Maghrib",tanggal_kini);
            }
        }

        if(Preference.getStatusIsyaPreference(getBaseContext()) == true){
            if(!time_isya.equals(Preference.getTimeIsyaPreference(getBaseContext()))){
                broadcastReceiverSholat.cancelAlarm(getBaseContext(),ALARM_CODE_ISYA);
                broadcastReceiverSholat.setRepeatingAlarm(getBaseContext(),ALARM_CODE_ISYA,
                        Preference.getTimeIsyaPreference(getBaseContext()),"Isya",tanggal_kini);
            }
        }

    }




}