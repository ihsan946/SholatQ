package com.ihsan946.sholatq.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.model.Sholatqmodel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import io.ipgeolocation.api.IPGeolocationAPI;

public class SplashScreen extends AppCompatActivity {

    String Ip_device;
    Sholatqmodel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //  access permission
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//
        getIp();
//

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 4000);




    }
    public void getIp() {

        String api_key = "e6a93eb6352441deba4e8801ab7c125d";
        IPGeolocationAPI ip = new IPGeolocationAPI(api_key);

//      get latitude and longitude
        String latitude,longitude;
        latitude = ip.getGeolocation().getLatitude();
        longitude = ip.getGeolocation().getLongitude();

        Preference.setLatitudePreferences(getBaseContext(),latitude);
        Preference.setLongitudePreferences(getBaseContext(),longitude);

//

        String ip_device;
        ip_device = ip.getGeolocation().getIPAddress();
//            Log.d("IP","IP sama dengan : "+ip_device);
        Ip_device = ip_device;
//        Log.d("IP","IP sama dengan : "+model.getIp_device());
        model = new Sholatqmodel();
        model.setIp_device(Ip_device);
        Preference.setIpPreferences(getBaseContext(),model);

    }



}