package com.ihsan946.sholatq.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.util.Log;

import com.ihsan946.sholatq.R;

import java.util.Calendar;

public class BroadcastReceiverSholat extends BroadcastReceiver {
    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 50;
    private NotificationUtils mNotificationUtils;
    private static final String EXTRA_JADWAL_SHOLAT = "jadwal_sholat";
    public static final String SHOLAT_CHANNEL_ID = "SHOLATQ.JADWALSHOLAT";
    public static final String SHOLAT_CHANNEL_NAME = "SHOLATQ";


    @Override
    public void onReceive(Context context, Intent intent) {

        String jadwal_sholat = intent.getStringExtra(EXTRA_JADWAL_SHOLAT);
        kirimNotif(context,jadwal_sholat);

    }


    private void kirimNotif(Context context , String jadwal_sholat){
        String name = "SholatQ";
        Uri alarmSound;
        mNotificationUtils = new NotificationUtils(context);
        Notification.Builder nb = mNotificationUtils.
                getAndroidChannelNotification(name,jadwal_sholat);
//
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .build();

        if(jadwal_sholat.equals("Shubuh")){
             alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://"+ context.getPackageName()
                    + "/" + R.raw.adzan_shubuh);
        }else{
             alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://"+ context.getPackageName()
                    + "/" + R.raw.adzan_bosnia);
        }

// create android channel
        NotificationChannel androidChannel = new NotificationChannel(SHOLAT_CHANNEL_ID,
                SHOLAT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

        androidChannel.enableLights(true);
        androidChannel.enableVibration(true);
        androidChannel.setLightColor(Color.WHITE);
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        androidChannel.setSound(alarmSound, audioAttributes);
//
        mNotificationUtils.getManager().createNotificationChannel(androidChannel);
        mNotificationUtils.getManager().notify(ALARM_REQUEST_CODE, nb.build());

    }

    public void setRepeatingAlarm(Context context, int ALARM_REQUEST_CODE, String time_sholat, String jadwal_sholat) {

        Intent alarmintent = new Intent(context,BroadcastReceiverSholat.class);
        pendingIntent = PendingIntent.getBroadcast(context,ALARM_REQUEST_CODE,alarmintent,0);
//
        String [] time_split;
        time_split = time_sholat.split("\\:");
        int jam = Integer.valueOf(time_split[0]);
        int menit = Integer.valueOf(time_split[1]);
//
        Calendar calendar = Calendar.getInstance();
        Calendar callset = (Calendar) calendar.clone();
        callset.set(Calendar.HOUR_OF_DAY,jam);
        callset.set(Calendar.MINUTE,menit);
        callset.setTimeInMillis(System.currentTimeMillis());
//
        Intent intent = new Intent(context, BroadcastReceiverSholat.class);
        intent.putExtra(EXTRA_JADWAL_SHOLAT, jadwal_sholat);
//
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        else
        {
            Log.d("ALARM", "SET ALARM FAILURE");

        }

    }

    public void cancelAlarm(Context context, int ALARM_REQUEST_CODE) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastReceiverSholat.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        else{
            Log.d("ALARM", "CANCEL FAILURE");
        }

    }


}
