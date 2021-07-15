package com.ihsan946.sholatq.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.api.ApiEndpointLokasi;
import com.ihsan946.sholatq.api.ApiEndpointQuotes;
import com.ihsan946.sholatq.api.ApiServiceLokasi;
import com.ihsan946.sholatq.api.ApiServiceQuotes;
import com.ihsan946.sholatq.model.ApimodelLokasi;
import com.ihsan946.sholatq.model.ApimodelQuotes;
import com.ihsan946.sholatq.model.Quotesmodel;
import com.ihsan946.sholatq.model.Sholatqmodel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import java.util.ArrayList;
import java.util.List;

import io.ipgeolocation.api.IPGeolocationAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    String Ip_device;
    Sholatqmodel model;
    String Hasil;
    String Quotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //  access permission
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//
        getIp();
        getLokasi();
        getTextQuotes();
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
//        String latitude,longitude;
//        latitude = ip.getGeolocation().getLatitude();
//        longitude = ip.getGeolocation().getLongitude();

//        Preference.setLatitudePreferences(getBaseContext(),latitude);
//        Preference.setLongitudePreferences(getBaseContext(),longitude);

//

        String ip_device;
        ip_device = ip.getGeolocation().getIPAddress();
//            Log.d("IP","IP sama dengan : "+ip_device);
        Ip_device = ip_device;
//        Log.d("IP","IP sama dengan : "+model.getIp_device());
        model = new Sholatqmodel();
        model.setIp_device(Ip_device);
        Preference.setIpPreferences(getBaseContext(), model);

    }

    //     String nama_kota,latitude,longitude;
    public void getLokasi() {
        ApiServiceLokasi api = ApiEndpointLokasi.getClient().create(ApiServiceLokasi.class);
        Call<ApimodelLokasi> call = api.getLokasi(Preference.getIpPreferences(getBaseContext()));
        call.enqueue(new Callback<ApimodelLokasi>() {
            @Override
            public void onResponse(Call<ApimodelLokasi> call, Response<ApimodelLokasi> response) {
                if (response.body().hasil.equals("API count exceeded - Increase Quota with Membership")) {

                    Log.d("Pesan", "Failure with api");
                } else{
                    Hasil = response.body().hasil;
                }

            }

            @Override
            public void onFailure(Call<ApimodelLokasi> call, Throwable t) {
                Log.d("Pesan", t.toString());
            }
        });


    }


//    public void getCity() {
//        String[] pecahkata = Hasil.split("\\n");
//
////        Log.d("Pecah",pecahkata[0]);
////        Log.d("Pecah",pecahkata[1]);
////        Log.d("Pecah",pecahkata[2]);
////        Log.d("Pecah",pecahkata[3]);
////        Log.d("Pecah",pecahkata[4]);
////        Log.d("Pecah",pecahkata[5]);
//
//        String hasil_pecahkota = pecahkata[3];
//        String hasil_pecahlatitude = pecahkata[4];
//        String hasil_pecahlongitude = pecahkata[5];
//
//        String[] pecahlatitude = hasil_pecahlatitude.split("\\s");
//        String[] pecahlongitude = hasil_pecahlongitude.split("\\s");
//        String[] pecahkota = hasil_pecahkota.split("\\s");
//
////        Log.d("PecahKota",pecahlatitude[0]);
////        Log.d("PecahKota",pecahlatitude[1]);
////        Log.d("PecahKota",pecahlongitude[0]);
////        Log.d("PecahKota",pecahlongitude[1]);
//
//        String Latitude = pecahlatitude[1];
//        String Longitude = pecahlongitude[1];
//        String Kota = pecahkota[1];
//
////
////
//        Preference.setLatitudePreferences(getBaseContext(), Latitude);
//        Preference.setLongitudePreferences(getBaseContext(), Longitude);
//        Preference.setKotaPreferences(getBaseContext(), Kota);
//
////        String latitude,longitude;
////        latitude = ip.getGeolocation().getLatitude();
////        longitude = ip.getGeolocation().getLongitude();
//
////        Preference.setLatitudePreferences(getBaseContext(),latitude);
////        Preference.setLongitudePreferences(getBaseContext(),longitude);

//    }

    public void getTextQuotes() {
        ApiServiceQuotes api = ApiEndpointQuotes.getClient().create(ApiServiceQuotes.class);

        Call<ApimodelQuotes> call = api.getQuotes();
        call.enqueue(new Callback<ApimodelQuotes>() {
            @Override
            public void onResponse(Call<ApimodelQuotes> call, Response<ApimodelQuotes> response) {
                Log.v("Hasil", response.body().quotesmodel.text_quotes);
                Quotes = response.body().quotesmodel.text_quotes;
                Preference.setQUOTES(getBaseContext(), Quotes);


            }

            @Override
            public void onFailure(Call<ApimodelQuotes> call, Throwable t) {
                Log.v("Hasil", t.toString());
            }
        });

//        Log.v("Hasil",t.toString());

    }


}