package com.ihsan946.sholatq.sharedpreferenced;

import android.content.Context;
import android.content.SharedPreferences;

import com.ihsan946.sholatq.model.Sholatqmodel;

public class Preference {

    private static final String PREF_SESSION = "com.ihsan946.sholatq.session";

    private static String IP_DEVICE = "IP_DEVICE";
    private static String NAMA_KOTA = "NAMA_KOTA";
    private static String LATITUDE = "LATITUDE";
    private static String LONGITUDE = "LONGITUDE";
    private static String QUOTES = "QUOTES";

    private Context context;

    public Preference(Context context) {
        this.context = context;
    }

//    Ip_Device
    public static void setIpPreferences(Context context, Sholatqmodel model) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(IP_DEVICE, model.getIp_device());
        editor.apply();
    }

    public static String getIpPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(IP_DEVICE, Utilstatic.DEFAULT_STRING);
    }


    //
    public static void setKotaPreferences(Context context, String kota){
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(NAMA_KOTA,kota);
        editor.apply();
    }

    public static String getNamaKota(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(NAMA_KOTA, Utilstatic.DEFAULT_STRING);

    }

//
// get latitude and logitude
    public static String getLatitudePreferences(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(LATITUDE, Utilstatic.DEFAULT_STRING);

    }

    public static void setLatitudePreferences(Context context, String latitude){
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(LATITUDE,latitude);
        editor.apply();
    }

    public static String getLongitudePreferences(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(LONGITUDE, Utilstatic.DEFAULT_STRING);

    }

    public static void setLongitudePreferences(Context context, String longitude){
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(LONGITUDE,longitude);
        editor.apply();
    }

    public static String getQUOTES(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(QUOTES, Utilstatic.DEFAULT_STRING);
    }

    public static void setQUOTES(Context context,String quotes) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(QUOTES,quotes);
        editor.apply();


    }

    //


}
