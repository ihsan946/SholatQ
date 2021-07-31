//NIM : 10118037
//NAMA : MUHAMMAD IHSAN
//KELAS : IF-1 2018
//Tanggal Pembuatan : 28 Juni 2021

package com.ihsan946.sholatq.sharedpreferenced;

import android.content.Context;
import android.content.SharedPreferences;

import com.ihsan946.sholatq.model.Sholatqmodel;

public class Preference {

    private static final String PREF_SESSION = "com.ihsan946.sholatq.session";

    private static final String IP_DEVICE = "IP_DEVICE";
    private static final String NAMA_KOTA = "NAMA_KOTA";
    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";
//
    private static final String QUOTES = "QUOTES";
//
    private static final String TIME_SHUBUH = "TIME_SHUBUH";
    private static final String TIME_DZUHUR = "TIME_DZUHUR";
    private static final String TIME_ASR = "TIME_ASR";
    private static final String TIME_MAGHRIB = "TIME_MAGHRIB";
    private static final String TIME_ISYA = "TIME_ISYA";
//
    private static final String STATUS_SHUBUH = "STATUS_SHUBUH";
    private static final String STATUS_DZUHUR = "STATUS_DZUHUR";
    private static final String STATUS_ASR = "STATUS_ASR";
    private static final String STATUS_MAGHRIB = "STATUS_MAGHRIB";
    private static final String STATUS_ISYA = "STATUS_ISYA";
//
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
    public static String getTimeShubuhPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(TIME_SHUBUH, Utilstatic.DEFAULT_STRING);
    }

    public static void setTimeShubuhPreference(Context context,String shubuh) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(TIME_SHUBUH,shubuh);
        editor.apply();
    }
//
    public static String getTimeDzuhurPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(TIME_DZUHUR, Utilstatic.DEFAULT_STRING);
    }

    public static void setTimeDzuhurPreference(Context context,String dzuhur) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(TIME_DZUHUR,dzuhur);
        editor.apply();
    }
//
    public static String getTimeAsrPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(TIME_ASR, Utilstatic.DEFAULT_STRING);
    }

    public static void setTimeAsrPreference(Context context,String asr) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(TIME_ASR,asr);
        editor.apply();
    }
//
    public static String getTimeMaghribPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(TIME_MAGHRIB, Utilstatic.DEFAULT_STRING);
    }

    public static void setTimeMaghribPreference(Context context,String maghrib) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(TIME_MAGHRIB,maghrib);
        editor.apply();
    }
//
    public static String getTimeIsyaPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(TIME_ISYA, Utilstatic.DEFAULT_STRING);
    }

    public static void setTimeIsyaPreference(Context context,String isya) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(TIME_ISYA,isya);
        editor.apply();
    }
//
    public static boolean getStatusShubuhPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getBoolean(STATUS_SHUBUH, Utilstatic.DEFAULT_BOOLEAN);
}

    public static void setStatusShubuhPreference(Context context,boolean shubuh) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(STATUS_SHUBUH,shubuh);
        editor.apply();
    }
//
    public static boolean getStatusDzuhurPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getBoolean(STATUS_SHUBUH, Utilstatic.DEFAULT_BOOLEAN);
}

    public static void setStatusDzuhurPreference(Context context,boolean dzuhur) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(STATUS_DZUHUR,dzuhur);
        editor.apply();
    }
//
    public static boolean getStatusAsrPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getBoolean(STATUS_ASR, Utilstatic.DEFAULT_BOOLEAN);
}

    public static void setStatusAsrPreference(Context context,boolean asr) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(STATUS_ASR,asr);
        editor.apply();
    }
//
    public static boolean getStatusMaghribPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getBoolean(STATUS_MAGHRIB, Utilstatic.DEFAULT_BOOLEAN);
}

    public static void setStatusMaghribPreference(Context context,boolean maghrib) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(STATUS_MAGHRIB,maghrib);
        editor.apply();
    }
//
    public static boolean getStatusIsyaPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getBoolean(STATUS_ISYA, Utilstatic.DEFAULT_BOOLEAN);
}

    public static void setStatusIsyaPreference(Context context,boolean isya) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(STATUS_ISYA,isya);
        editor.apply();
    }


}
