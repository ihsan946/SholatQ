//NIM : 10118037
//NAMA : MUHAMMAD IHSAN
//KELAS : IF-1 2018
//Tanggal Pembuatan : 28 Juni 2021

package com.ihsan946.sholatq.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.api.ApiEndpointLokasi;
import com.ihsan946.sholatq.api.ApiEndpointQuotes;
import com.ihsan946.sholatq.api.ApiServiceLokasi;
import com.ihsan946.sholatq.api.ApiServiceQuotes;
import com.ihsan946.sholatq.model.ApimodelLokasi;
import com.ihsan946.sholatq.model.ApimodelQuotes;
import com.ihsan946.sholatq.model.Sholatqmodel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    Sholatqmodel model;
    String Ip_device,Quotes;
    boolean koneksi_internet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //  access permission
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//
//        getIp();
    cekKoneksiDevice();

//

        new Handler().postDelayed(() -> {
            if(!koneksi_internet){
//                Toast.makeText(getBaseContext(), "Maaf Untuk Aplikasi Ini Menggunakan Internet",
//                        Toast.LENGTH_LONG).show();
//                finish();
                Intent intent = new Intent(SplashScreen.this, Noinet.class);
                startActivity(intent);
                finish();
            }
            else{
                getLokasi();
                getTextQuotes();
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        }, 4000);


    }

    public  boolean isInternetConnection()
    {
        ConnectivityManager connectivityManager =  (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void cekKoneksiDevice(){

        ConnectivityManager connectivityManager;
        try {

            connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){

                @Override
                public void onAvailable(@NonNull Network network) {
                    koneksi_internet = true;
                }

                @Override
                public void onLost(@NonNull Network network) {
                    koneksi_internet = false;
                }
            });




        }catch (Exception e){

            koneksi_internet = false;

        }

    }


//    public void getIp() {
//
//        String api_key = "341625ee30eb4ab6af1132b0a4e20890";
//        IPGeolocationAPI ip = new IPGeolocationAPI(api_key);
//
////      get latitude and longitude
////        String latitude,longitude;
////        latitude = ip.getGeolocation().getLatitude();
////        longitude = ip.getGeolocation().getLongitude();
//
////        Preference.setLatitudePreferences(getBaseContext(),latitude);
////        Preference.setLongitudePreferences(getBaseContext(),longitude);
//
////
//
//        String ip_device;
//        ip_device = ip.getGeolocation().getIPAddress();
////            Log.d("IP","IP sama dengan : "+ip_device);
//        Ip_device = ip_device;
////        Log.d("IP","IP sama dengan : "+model.getIp_device());
//        model = new Sholatqmodel();
//        model.setIp_device(Ip_device);
//        Preference.setIpPreferences(getApplicationContext(), model);
//
//
//    }

    //     String nama_kota,latitude,longitude;
    public void getLokasi() {
        ApiServiceLokasi api = ApiEndpointLokasi.getClient().create(ApiServiceLokasi.class);
        Call<ApimodelLokasi> call = api.getLokasi();
        call.enqueue(new Callback<ApimodelLokasi>() {
            @Override
            public void onResponse(Call<ApimodelLokasi> call, Response<ApimodelLokasi> response) {
//                Log.d("Pesan", response.body().kota);
//                Log.d("Pesan", response.body().latitude);
//                Log.d("Pesan", response.body().longitude);
                Preference.setKotaPreferences(getBaseContext(),response.body().kota);
                Preference.setLatitudePreferences(getBaseContext(),response.body().latitude);
                Preference.setLongitudePreferences(getBaseContext(),response.body().longitude);

            }

            @Override
            public void onFailure(Call<ApimodelLokasi> call, Throwable t) {
                Log.d("Pesan", t.toString());
            }
        });
//

    }



    public void getTextQuotes() {
        ApiServiceQuotes api = ApiEndpointQuotes.getClient().create(ApiServiceQuotes.class);

        Call<ApimodelQuotes> call = api.getQuotes();
        call.enqueue(new Callback<ApimodelQuotes>() {
            @Override
            public void onResponse(Call<ApimodelQuotes> call, Response<ApimodelQuotes> response) {
                if((response.body().quotesmodel != null)){
                    Log.v("Hasil", response.body().quotesmodel.text_quotes);
                    Quotes = response.body().quotesmodel.text_quotes;
                    Preference.setQUOTES(getBaseContext(), Quotes);
                }
                else{
                    Preference.setQUOTES(getBaseContext(),"Ada gangguan dengan Penyedia Quotes");
                }


            }

            @Override
            public void onFailure(Call<ApimodelQuotes> call, Throwable t) {
                Log.v("Hasil", t.toString());
            }
        });

//        Log.v("Hasil",t.toString());

    }



}